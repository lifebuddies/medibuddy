package com.medibuddy.webapi.repository.analysis;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medibuddy.webapi.entity.analysis.Analysis;
import com.medibuddy.webapi.entity.analysis.Diagnosis;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, UUID> {

	@Query("SELECT d FROM Diagnosis d WHERE d.analysis = ?1")
	Optional<Diagnosis> findByAnalysisRequest(Analysis analysis);

}
