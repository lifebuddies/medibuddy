package com.medibuddy.webapi.repository.analysis;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.medibuddy.webapi.entity.analysis.AnalysisType;
import com.medibuddy.webapi.entity.analysis.DiseaseAdvice;
import com.medibuddy.webapi.entity.analysis.Diagnosis.Criticality;

import java.util.List;


public interface DiseaseAdviceRepository extends JpaRepository<DiseaseAdvice, UUID> {

	@Query("SELECT da FROM DiseaseAdvice da WHERE da.analysisType = ?1 AND da.criticality = ?2")
	List<DiseaseAdvice> findByCriticalityAndAnalysisType(Criticality criticality, AnalysisType analysisType);

}
