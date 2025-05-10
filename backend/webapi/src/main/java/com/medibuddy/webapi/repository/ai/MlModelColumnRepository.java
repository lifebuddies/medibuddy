package com.medibuddy.webapi.repository.ai;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medibuddy.webapi.entity.ai.MlModelColumn;

@Repository
public interface MlModelColumnRepository extends JpaRepository<MlModelColumn, UUID> {

	MlModelColumn findByName(String name);

}
