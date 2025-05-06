package com.medibuddy.webapi.presentation.rest.v1.dto.analysis;

import jakarta.validation.constraints.NotBlank;

public record AnalysisRequest(
	@NotBlank String analysisType
)  {

}
