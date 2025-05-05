package com.medibuddy.webapi.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.medibuddy.webapi.entity.analysis.MedicalRecord;
import com.medibuddy.webapi.util.FloatUtils;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;

public class MlModelInputMapBuilder {

	private OrtEnvironment ortEnv;
	private List<MedicalRecord> inputs;
	private Map<String, OnnxTensor> inputMap;

	public MlModelInputMapBuilder(List<MedicalRecord> inputs) {
		this(OrtEnvironment.getEnvironment(), inputs);
	}

	public MlModelInputMapBuilder(OrtEnvironment ortEnv, List<MedicalRecord> inputs) {
		this.ortEnv = ortEnv;
		this.inputs = inputs;
		this.inputMap = new HashMap<>();
	}


	public MlModelInputMapBuilder column(String columnName, Function<MedicalRecord, Object> mapFunction) throws OrtException {
		switch (mapFunction.apply(inputs.get(0))) {
			case Boolean i:
				// FIXME: This hack isn't gurenteed to work for all cases
				inputMap.put(columnName, OnnxTensor.createTensor(ortEnv, inputs.stream().map(mapFunction).toArray(Boolean[]::new)));
				break;
			case Integer i:
				inputMap.put(columnName, OnnxTensor.createTensor(ortEnv, inputs.stream().mapToInt(record -> (int)mapFunction.apply(record)).toArray()));
				break;
			case Long l:
				inputMap.put(columnName, OnnxTensor.createTensor(ortEnv, inputs.stream().mapToLong(record -> (long)mapFunction.apply(record)).toArray()));
				break;
			case Float f:
				inputMap.put(columnName, OnnxTensor.createTensor(ortEnv, FloatUtils.toFloatArray(inputs.stream().map(record -> (float)mapFunction.apply(record)), inputs.size())));
				break;
			case Double d:
				inputMap.put(columnName, OnnxTensor.createTensor(ortEnv, inputs.stream().mapToDouble(record -> (double)mapFunction.apply(record)).toArray()));
				break;
			case String s:
				inputMap.put(columnName, OnnxTensor.createTensor(ortEnv, inputs.stream().map(record -> mapFunction.apply(record)).toArray(String[]::new)));
				break;
			default:
				inputMap.put(columnName, OnnxTensor.createTensor(ortEnv, inputs.stream().map(record -> mapFunction.apply(record)).toArray()));
				break;
		}
		return this;
	}

	public Map<String, OnnxTensor> build() {
		return inputMap;
	}
}
