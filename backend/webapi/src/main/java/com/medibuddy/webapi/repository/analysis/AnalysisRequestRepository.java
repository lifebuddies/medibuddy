package com.medibuddy.webapi.repository.analysis;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.medibuddy.webapi.entity.analysis.Analysis;

@Repository
public interface AnalysisRequestRepository extends JpaRepository<Analysis, UUID> {
}
