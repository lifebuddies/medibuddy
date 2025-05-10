package com.medibuddy.webapi.presentation.rest.v1.dto.record;

import java.time.Instant;
import java.util.UUID;

public record MedicalRecordFieldValueResponse(
	UUID id,
	String name,
	String value,
	Instant date
) {
	
}
