package com.medibuddy.webapi;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.medibuddy.webapi.ai.model.DietRecommendationMlModelPipeline;
import com.medibuddy.webapi.entity.ai.MlModelInputs;

@EnableJpaAuditing
@SpringBootApplication
public class WebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebapiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			var model = new DietRecommendationMlModelPipeline();
			System.out.println("Model Prediction: " + model.predict(List.of(MlModelInputs.builder()
					.age(56.f)
					.gender("Male")
					.weightKg(58.4f)
					.heightCm(160.f)
					.BMI(22.8f)
					.diseaseType("Obesity")
					.severity("Moderate")
					.physicalActivityLevel("Moderate")
					.dailyCaloricIntake(3079.f)
					.cholesterolMgDL(173.3f)
					.bloodPressureMmHg(133.f)
					.glucoseMgDL(116.3f)
					.dietaryRestrictions("Low_Sugar")
					.allergy("Peanuts")
					.preferredCuisine("Mexican")
					.weeklyExerciseHours(3.1f)
					.adherenceToDietPlan(96.6f)
					.dietaryNutrientImbalanceScore(3.1f)
					.build())));
		};
	}

}
