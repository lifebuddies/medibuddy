package com.medibuddy.webapi.config.init;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.medibuddy.webapi.ai.MlModelPipeline;
import com.medibuddy.webapi.ai.model.DietRecommendationMlModelPipeline;
import com.medibuddy.webapi.entity.analysis.AnalysisType;
import com.medibuddy.webapi.repository.ai.MlModelRepository;
import com.medibuddy.webapi.repository.analysis.AnalysisTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MlModelBlueprintLoader implements ApplicationRunner {

	@Autowired
	private MlModelRepository mlModelRepository;

	@Autowired
	private AnalysisTypeRepository analysisTypeRepository;

	private static Map<String, MlModelPipeline> MODELS;

	public static MlModelPipeline getModel(String modelName) {
		return MODELS.get(modelName);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		MODELS = new HashMap<>();
		// List<MlModelBlueprint> models = mlModelRepository.findAll();
		// for (MlModelBlueprint model : models) {
		// 	MODELS.put(model.getName(), new NoOpMlModelPipeline(model.getName(), model));
		// 	log.info("Loaded model: " + model.getName());
		// }

		var dietModel = new DietRecommendationMlModelPipeline();
		mlModelRepository.saveAndFlush(dietModel.getBlueprint());
		MODELS.put(DietRecommendationMlModelPipeline.MODEL_NAME, dietModel);
		log.info("Loaded model: " + DietRecommendationMlModelPipeline.MODEL_NAME);

		for (MlModelPipeline model : MODELS.values()) {
			var blueprint = model.getBlueprint();
			var analysisType = new AnalysisType(blueprint.getName(), blueprint.getName(), "en", blueprint);
			analysisTypeRepository.save(analysisType);
			log.info("Loaded analysis type: " + blueprint.getName());
		}
		analysisTypeRepository.flush();
	}

}
