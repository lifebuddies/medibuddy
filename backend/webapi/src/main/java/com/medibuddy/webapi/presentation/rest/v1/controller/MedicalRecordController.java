package com.medibuddy.webapi.presentation.rest.v1.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.medibuddy.webapi.presentation.rest.v1.dto.record.MedicalRecordFieldModificationRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.record.MedicalRecordFieldValueResponse;
import com.medibuddy.webapi.service.MedicalRecordService;
import com.medibuddy.webapi.shared.ApiResponse;
import com.medibuddy.webapi.shared.PaginatedApiResponse;

@RestController
@RequestMapping("/api/v1/medical-records")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@PreAuthorize("hasAuthority('SCOPE_record.write')")
	@PostMapping
	public ResponseEntity<ApiResponse<List<MedicalRecordFieldValueResponse>>> submitMedicalRecord(
			Principal user,
			@RequestBody List<MedicalRecordFieldModificationRequest> inputs) {
		var username = user.getName();
		var result = medicalRecordService.submitMedicalRecord(username, inputs);
		var response = ApiResponse.success(result, "Medical record submitted successfully!", HttpStatus.CREATED);
		return response.toResponseEntity();
	}

	@PreAuthorize("hasAuthority('SCOPE_record.read')")
	@GetMapping
	public ResponseEntity<PaginatedApiResponse<MedicalRecordFieldValueResponse>> getAllMedicalRecords(
			Principal user,
			Pageable pageable) {
		var username = user.getName();
		var result = medicalRecordService.getAllMedicalRecords(username, pageable);
		var response = ApiResponse.success(result, "All Medical records retrieved successfully!");
		var paginatedResponse = PaginatedApiResponse.of(response);
		return paginatedResponse.toResponseEntity();
	}

}
