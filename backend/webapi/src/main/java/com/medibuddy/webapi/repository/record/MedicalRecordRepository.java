package com.medibuddy.webapi.repository.record;

import java.util.Optional;
import java.util.UUID;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medibuddy.webapi.entity.analysis.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    @Query("SELECT m FROM MedicalRecord m WHERE m.user.username = ?1")
    Page<MedicalRecord> findByUsername(String username, org.springframework.data.domain.Pageable pageable);

    @Query("""
                SELECT m FROM MedicalRecord m
                WHERE m.user.username = ?1
                  AND m.column.name = ?2
                ORDER BY m.updatedAt DESC
            """)
    Optional<MedicalRecord> findTopByUserAndColumnNameOrderByUpdatedAtDesc(
            String username,
            String columnName);

}
