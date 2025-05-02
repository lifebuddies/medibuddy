package com.medibuddy.webapi.repository.ai;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medibuddy.webapi.entity.ai.MlModelBlueprint;

public interface MlModelRepository extends JpaRepository<MlModelBlueprint, UUID> {

    Optional<MlModelBlueprint> findByName(String modelName);

}
