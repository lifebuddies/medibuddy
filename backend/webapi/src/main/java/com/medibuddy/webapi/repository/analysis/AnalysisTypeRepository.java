package com.medibuddy.webapi.repository.analysis;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.medibuddy.webapi.entity.analysis.AnalysisType;

@Repository
public interface AnalysisTypeRepository extends JpaRepository<AnalysisType, UUID> {
    AnalysisType findByName(String name);
}
