package com.medibuddy.webapi.presentation.rest.v1.dto.analysis;

import java.util.List;
import java.util.UUID;

public record DiagnosisResponse(UUID diagnosisId, String diagnosisResult, List<String> advice) {

}
