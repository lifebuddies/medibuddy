package com.medibuddy.webapi.ai.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.medibuddy.webapi.ai.MlModelPipeline;
import com.medibuddy.webapi.entity.ai.MlModelBlueprint;
import com.medibuddy.webapi.entity.ai.MlModelInputs;

import ai.onnxruntime.OrtException;

public class NoOpMlModelPipeline extends MlModelPipeline {

	public NoOpMlModelPipeline(String modelName, MlModelBlueprint blueprint) throws OrtException, IOException {
		super(modelName, blueprint);
	}

	@Override
	public List<String> predict(List<MlModelInputs> input) {
		// This is a no-op model, so we just return an empty string list
		return Arrays.asList(new String[input.size()]);
	}

}
