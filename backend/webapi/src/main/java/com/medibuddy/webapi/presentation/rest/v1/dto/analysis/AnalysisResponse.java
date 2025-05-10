package com.medibuddy.webapi.presentation.rest.v1.dto.analysis;

import java.time.Instant;
import java.util.UUID;

public record AnalysisResponse(UUID analysisId, String name, Instant requestedAt)  {

}
