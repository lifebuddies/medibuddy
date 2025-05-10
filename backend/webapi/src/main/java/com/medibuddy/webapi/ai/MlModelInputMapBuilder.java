package com.medibuddy.webapi.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.medibuddy.webapi.entity.ai.MlModelColumn.DataType;
import com.medibuddy.webapi.entity.ai.MlModelInputs;
import com.medibuddy.webapi.entity.analysis.MedicalRecord;
import com.medibuddy.webapi.util.FloatUtils;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;

public class MlModelInputMapBuilder {

	private OrtEnvironment ortEnv;
	private List<MlModelInputs> inputs;
	private Map<String, OnnxTensor> inputMap;

	public MlModelInputMapBuilder(List<MlModelInputs> inputs) {
		this(OrtEnvironment.getEnvironment(), inputs);
	}

	public MlModelInputMapBuilder(OrtEnvironment ortEnv, List<MlModelInputs> inputs) {
		this.ortEnv = ortEnv;
		this.inputs = inputs;
		this.inputMap = new HashMap<>();
	}


	public MlModelInputMapBuilder column(String columnName, Function<MlModelInputs, Object> mapFunction) throws OrtException {
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


    public static Map<String, OnnxTensor> convertMedicalRecordsToSingltonMlModelInputMap(List<MedicalRecord> inputs) throws OrtException {
        OrtEnvironment ortEnv = OrtEnvironment.getEnvironment();
        Map<String, OnnxTensor> inputMap = new HashMap<>();
        for (MedicalRecord input : inputs) {
            String columnName = input.getColumn().getName();
            Object value = parseStringToDataType(input.getValue(), input.getColumn().getDataType());
            Object tensorValue = convertSingletonToTensorInput(value);
            inputMap.put(columnName, OnnxTensor.createTensor(ortEnv, tensorValue));
        }
        return inputMap;
    }


    public static Object parseStringToDataType(String value, DataType dataType) {
        switch (dataType) {
            case STRING:
                return value;
            case LONG:
                return Long.parseLong(value);
            case DOUBLE:
                return Double.parseDouble(value);
            case FLOAT:
                return Float.parseFloat(value);
            case BOOLEAN:
                return Boolean.parseBoolean(value);
            default:
                throw new IllegalArgumentException("Unsupported data type: " + dataType);
        }
    }
    
    public static Object convertSingletonToTensorInput(Object inputColumn)  {
        if (inputColumn == null) {
            throw new IllegalArgumentException("Input column cannot be null");
        }
		switch (inputColumn) {
			case Boolean i:
				// FIXME: This hack isn't gurenteed to work for all cases
				return List.of(inputColumn).stream().toArray(Boolean[]::new);
			case Integer i:
				return List.of(inputColumn).stream().mapToInt(record -> (int)record).toArray();
			case Long l:
				return List.of(inputColumn).stream().mapToLong(record -> (long)record).toArray();
			case Float f:
				return FloatUtils.toFloatArray(List.of(inputColumn).stream().map(record -> (float)record), 1);
			case Double d:
				return List.of(inputColumn).stream().mapToDouble(record -> (double)record).toArray();
			case String s:
				return List.of(inputColumn).stream().map(record -> record).toArray(String[]::new);
			default:
				return List.of(inputColumn).stream().map(record -> record).toArray();
		}
    }

}
