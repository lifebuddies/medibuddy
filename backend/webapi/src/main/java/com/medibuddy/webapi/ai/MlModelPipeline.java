package com.medibuddy.webapi.ai;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.medibuddy.webapi.entity.ai.MlModelBlueprint;
import com.medibuddy.webapi.entity.ai.MlModelInputs;
import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;

public abstract class MlModelPipeline {

	protected static final String PREPROCESSOR_MODEL_PATH_TEMPLATE = "models/%s.preprocess.onnx";
	protected static final String MAIN_MODEL_PATH_TEMPLATE = "models/%s.main.onnx";
	protected static final String POSTPROCESSOR_MODEL_PATH_TEMPLATE = "models/%s.postprocess.onnx";

	protected final OrtSession preprocessor;
	protected final OrtSession model;
	protected final OrtSession postprocessor;

	protected final MlModelBlueprint blueprint;

	public MlModelPipeline(String modelName, MlModelBlueprint blueprint) throws OrtException, IOException {
		var ortEnv = OrtEnvironment.getEnvironment();
		var ops = new OrtSession.SessionOptions();
		var preprocessModel = getClass().getClassLoader()
				.getResourceAsStream(String.format(PREPROCESSOR_MODEL_PATH_TEMPLATE, modelName));
		var mainModel = getClass().getClassLoader()
				.getResourceAsStream(String.format(MAIN_MODEL_PATH_TEMPLATE, modelName));
		var postprocessModel = getClass().getClassLoader()
				.getResourceAsStream(String.format(POSTPROCESSOR_MODEL_PATH_TEMPLATE, modelName));
		if (preprocessModel == null || mainModel == null || postprocessModel == null) {
			throw new IllegalArgumentException("Model files not found for model: " + modelName);
		}
		this.preprocessor = ortEnv.createSession(preprocessModel.readAllBytes(), ops);
		this.model = ortEnv.createSession(mainModel.readAllBytes(), ops);
		this.postprocessor = ortEnv.createSession(postprocessModel.readAllBytes(), ops);
		this.blueprint = blueprint;
	}

	public abstract List<String> predict(List<MlModelInputs> input) throws OrtException;

	public List<String> predict(Map<String, OnnxTensor> inputMap) throws OrtException {
		// Run the preprocessing model
		OrtSession.Result preprocessResult = preprocessor.run(inputMap);
		OnnxTensor preprocessedData = (OnnxTensor) preprocessResult.get(0);

		// === Step 2: Main Model Inference ===
		Map<String, OnnxTensor> mainInputs = Collections.singletonMap("float_input", preprocessedData);
		OrtSession.Result mainResult = model.run(mainInputs);
		OnnxTensor predictionTensor = (OnnxTensor) mainResult.get(0);
		long[] predictedLabel = (long[]) predictionTensor.getValue();

		// === Step 3: Postprocessing Model ===
		Map<String, OnnxTensor> postprocessInputs = Collections.singletonMap("predicted_label",
				OnnxTensor.createTensor(OrtEnvironment.getEnvironment(), predictedLabel));
		OrtSession.Result postprocessResult = postprocessor.run(postprocessInputs);
		OnnxTensor recommendationTensor = (OnnxTensor) postprocessResult.get(0);
		String[] predictions = (String[]) recommendationTensor.getValue();

		return Arrays.asList(predictions).stream().map(String::valueOf).toList();
	}

}
