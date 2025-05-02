package com.medibuddy.webapi.config.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import com.medibuddy.webapi.ai.MlModelPipeline;
import com.medibuddy.webapi.entity.ai.MlModelBlueprint;
import com.medibuddy.webapi.repository.ai.MlModelRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MlModelBlueprintLoader implements ApplicationRunner {

	@Autowired
	private MlModelRepository mlModelRepository;

	public static Map<String, MlModelPipeline> MODELS;

	@Override
    public void run(ApplicationArguments args) throws Exception {
		MODELS = new HashMap<>();
		List<MlModelBlueprint> models = mlModelRepository.findAll();
		for (MlModelBlueprint model : models) {
			MODELS.put(model.getName(), new MlModelPipeline(model.getName(), model));
			log.info("Loaded model: " + model.getName());
		}
	}

}
