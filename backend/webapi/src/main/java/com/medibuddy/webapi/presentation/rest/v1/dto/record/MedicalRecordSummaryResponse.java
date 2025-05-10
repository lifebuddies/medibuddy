package com.medibuddy.webapi.presentation.rest.v1.dto.record;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.domain.Page;

public record MedicalRecordSummaryResponse(Instant lastUpdatedAt, Page<UUID> recordIds)  {

}
