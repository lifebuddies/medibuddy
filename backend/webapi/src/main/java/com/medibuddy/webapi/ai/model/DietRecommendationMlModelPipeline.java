package com.medibuddy.webapi.ai.model;

import java.io.IOException;
import java.util.List;
import com.medibuddy.webapi.ai.MlModelInputMapBuilder;
import com.medibuddy.webapi.ai.MlModelPipeline;
import com.medibuddy.webapi.entity.ai.MlModelInputs;
import ai.onnxruntime.OrtException;

public class DietRecommendationMlModelPipeline extends MlModelPipeline {
	
	public static final String MODEL_NAME = "diet_recommendation";

	public DietRecommendationMlModelPipeline() throws OrtException, IOException {
		super(MODEL_NAME, null);
	}

	@Override
	public List<String> predict(List<MlModelInputs> input) throws OrtException {
		var inputMap = new MlModelInputMapBuilder(input)
				.column("Age", MlModelInputs::getAge)
				.column("Gender", MlModelInputs::getGender)
				.column("Weight_kg", MlModelInputs::getWeightKg)
				.column("Height_cm", MlModelInputs::getHeightCm)
				.column("BMI", MlModelInputs::getBMI)
				.column("Disease_Type", MlModelInputs::getDiseaseType)
				.column("Severity", MlModelInputs::getSeverity)
				.column("Physical_Activity_Level", MlModelInputs::getPhysicalActivityLevel)
				.column("Daily_Caloric_Intake", MlModelInputs::getDailyCaloricIntake)
				.column("Cholesterol_mg_dL", MlModelInputs::getCholesterolMgDL)
				.column("Blood_Pressure_mmHg", MlModelInputs::getBloodPressureMmHg)
				.column("Glucose_mg_dL", MlModelInputs::getGlucoseMgDL)
				.column("Dietary_Restrictions", MlModelInputs::getDietaryRestrictions)
				.column("Allergies", MlModelInputs::getAllergy)
				.column("Preferred_Cuisine", MlModelInputs::getPreferredCuisine)
				.column("Weekly_Exercise_Hours", MlModelInputs::getWeeklyExerciseHours)
				.column("Adherence_to_Diet_Plan", MlModelInputs::getAdherenceToDietPlan)
				.column("Dietary_Nutrient_Imbalance_Score", MlModelInputs::getDietaryNutrientImbalanceScore)
				.build();
		return predict(inputMap);
	}

}
