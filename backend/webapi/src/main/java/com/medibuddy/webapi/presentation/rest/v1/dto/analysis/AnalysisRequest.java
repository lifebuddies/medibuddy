package com.medibuddy.webapi.presentation.rest.v1.dto.analysis;

import java.util.List;

import com.medibuddy.webapi.presentation.rest.v1.dto.record.MedicalRecordFieldModificationRequest;

public record AnalysisRequest(
	List<MedicalRecordFieldModificationRequest> inputs
)  {

}
