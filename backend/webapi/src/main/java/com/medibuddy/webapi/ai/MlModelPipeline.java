package com.medibuddy.webapi.ai;

import java.io.IOException;
import java.util.List;
import com.medibuddy.webapi.entity.ai.MlModelBlueprint;
import com.medibuddy.webapi.entity.analysis.MedicalRecord;
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

	public abstract List<String> predict(List<MedicalRecord> input) throws OrtException;

}
