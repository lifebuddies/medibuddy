package com.medibuddy.webapi.ai.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.medibuddy.webapi.ai.MlModelInputMapBuilder;
import com.medibuddy.webapi.ai.MlModelPipeline;
import com.medibuddy.webapi.entity.analysis.MedicalRecord;
import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;

public class DietRecommendationMlModelPipeline extends MlModelPipeline {
	
	public static final String MODEL_NAME = "diet_recommendation";

	public DietRecommendationMlModelPipeline() throws OrtException, IOException {
		super(MODEL_NAME, null);
	}

	@Override
	public List<String> predict(List<MedicalRecord> input) throws OrtException {
		var inputMap = new MlModelInputMapBuilder(input)
				.column("Age", record -> (float)record.getAge())
				.column("Gender", MedicalRecord::getGender)
				.column("Weight_kg", MedicalRecord::getWeightKg)
				.column("Height_cm", MedicalRecord::getHeightCm)
				.column("BMI", MedicalRecord::getBMI)
				.column("Disease_Type", MedicalRecord::getDiseaseType)
				.column("Severity", MedicalRecord::getSeverity)
				.column("Physical_Activity_Level", MedicalRecord::getPhysicalActivityLevel)
				.column("Daily_Caloric_Intake", MedicalRecord::getDailyCaloricIntake)
				.column("Cholesterol_mg_dL", MedicalRecord::getCholesterolMgDL)
				.column("Blood_Pressure_mmHg", MedicalRecord::getBloodPressureMmHg)
				.column("Glucose_mg_dL", MedicalRecord::getGlucoseMgDL)
				.column("Dietary_Restrictions", MedicalRecord::getDietaryRestrictions)
				.column("Allergies", MedicalRecord::getAllergy)
				.column("Preferred_Cuisine", MedicalRecord::getPreferredCuisine)
				.column("Weekly_Exercise_Hours", MedicalRecord::getWeeklyExerciseHours)
				.column("Adherence_to_Diet_Plan", MedicalRecord::getAdherenceToDietPlan)
				.column("Dietary_Nutrient_Imbalance_Score", MedicalRecord::getDietaryNutrientImbalanceScore)
				.build();


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
