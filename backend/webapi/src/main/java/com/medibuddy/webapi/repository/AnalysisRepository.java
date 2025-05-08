package com.medibuddy.webapi.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medibuddy.webapi.entity.Analysis;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, UUID> {
    Page<Analysis> findByType(String type, Pageable pageable);
    Page<Analysis> findByPatient(String patient, Pageable pageable);
}
