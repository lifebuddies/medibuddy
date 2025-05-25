package com.medibuddy.webapi.ai.model;

import java.io.IOException;
import java.util.List;
import com.medibuddy.webapi.ai.MlModelInputMapBuilder;
import com.medibuddy.webapi.ai.MlModelPipeline;
import com.medibuddy.webapi.entity.ai.MlModelInputs;
import com.medibuddy.webapi.entity.ai.MlModelBlueprint.State;
import com.medibuddy.webapi.entity.ai.MlModelColumn.DataType;

import ai.onnxruntime.OrtException;

public class DietRecommendationMlModelPipeline extends MlModelPipeline {

	public static final String MODEL_NAME = "diet_recommendation";

	public DietRecommendationMlModelPipeline() throws OrtException, IOException {
		super(MODEL_NAME, new MlModelBlueprint(
				MODEL_NAME,
				"""
### Overview
This supervised classification model predicts personalized dietary plans based on a patient's health, lifestyle, and preferences. Trained on 1,101 records from the Kaggle Diet Recommendations Dataset, it outputs one of three dietary categories.

### Objective
Recommend the optimal diet plan:
- **Low_Carb** - For diabetes or weight-related issues  
- **Low_Sodium** - For hypertension or cardiovascular health  
- **Balanced** - For general wellness or non-specific needs

### Input Features
- **Demographics**: Age, Gender  
- **Vitals & Health**: Weight, Height, BMI, Disease Type, Severity  
- **Clinical Data**: Blood Pressure, Cholesterol, Glucose  
- **Lifestyle**: Physical Activity Level, Weekly Exercise Hours, Daily Caloric Intake  
- **Dietary Info**: Dietary Restrictions, Allergies, Preferred Cuisine  
- **Behavioral**: Adherence to Diet Plan, Nutrient Imbalance Score

### Target
- `Diet_Recommendation` (Balanced, Low_Carb, Low_Sodium)

### Use Cases
- Digital health & wellness platforms  
- Nutritionist support tools  
- Personalized meal planning apps

### Benefits
- Tailored dietary guidance  
- Support for chronic condition management  
- Scalable and accessible nutrition recommendations
				""",
				"en",
				State.RUNNING,
				List.of(
						new MlModelColumn("Age", DataType.FLOAT, "Age of the patient.", "en"),
						new MlModelColumn("Gender", DataType.STRING, "Gender of the patient (Male, Female).", "en"),
						new MlModelColumn("Weight_kg", DataType.FLOAT, "Weight of the patient in kilograms.", "en"),
						new MlModelColumn("Height_cm", DataType.FLOAT, "Height of the patient in centimeters.", "en"),
						new MlModelColumn("BMI", DataType.FLOAT,
								"Body Mass Index calculated as Weight (kg) / (Height (m)^2).", "en"),
						new MlModelColumn("Disease_Type", DataType.STRING,
								"Primary disease diagnosed (Diabetes, Hypertension, Obesity, or None).", "en"),
						new MlModelColumn("Severity", DataType.STRING,
								"Severity of the disease (Mild, Moderate, Severe).", "en"),
						new MlModelColumn("Physical_Activity_Level", DataType.STRING,
								"Level of physical activity (Sedentary, Moderate, Active).", "en"),
						new MlModelColumn("Daily_Caloric_Intake", DataType.FLOAT, "Average daily calorie consumption.",
								"en"),
						new MlModelColumn("Cholesterol_mg_dL", DataType.FLOAT, "Cholesterol level in mg/dL.", "en"),
						new MlModelColumn("Blood_Pressure_mmHg", DataType.FLOAT, "Average blood pressure in mmHg.",
								"en"),
						new MlModelColumn("Glucose_mg_dL", DataType.FLOAT, "Blood glucose level in mg/dL.", "en"),
						new MlModelColumn("Dietary_Restrictions", DataType.STRING,
								"Known dietary restrictions (Low_Sodium, Low_Sugar, None).", "en"),
						new MlModelColumn("Allergies", DataType.STRING, "Known food allergies (Peanuts, Gluten, None).",
								"en"),
						new MlModelColumn("Preferred_Cuisine", DataType.STRING,
								"Patient's preferred cuisine (Indian, Italian, Chinese, Mexican).", "en"),
						new MlModelColumn("Weekly_Exercise_Hours", DataType.FLOAT,
								"Number of hours exercised per week.", "en"),
						new MlModelColumn("Adherence_to_Diet_Plan", DataType.FLOAT,
								"Patient's adherence to a suggested diet plan (percentage).", "en"),
						new MlModelColumn("Dietary_Nutrient_Imbalance_Score", DataType.FLOAT,
								"A score indicating nutrient imbalance in the patient's diet.", "en")
						),
				new MlModelColumn("Diet_Recommendation", DataType.STRING,
								"Suggested dietary plan based on individual health conditions:\n" +
										"Low_Carb: For managing diabetes or weight-related concerns.\n" +
										"Low_Sodium: For hypertension or cardiovascular health.\n" +
										"Balanced: For general health improvement or non-specific requirements.",
								"en")));
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
