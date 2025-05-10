package com.medibuddy.webapi.presentation.rest.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

	@PostMapping
	public ResponseEntity<ApiResponse<List<MedicalRecordFieldValueResponse>>> submitMedicalRecord(
			@AuthenticationPrincipal(expression = "attributes['sub']") String username,
			@RequestBody List<MedicalRecordFieldModificationRequest> inputs) {
		var result = medicalRecordService.submitMedicalRecord(username, inputs);
		var response = ApiResponse.success(result, "Medical record submitted successfully!", HttpStatus.CREATED);
		return response.toResponseEntity();
	}

	@GetMapping
	public ResponseEntity<PaginatedApiResponse<MedicalRecordFieldValueResponse>> getAllMedicalRecords(
			@AuthenticationPrincipal(expression = "attributes['sub']") String username,
			Pageable pageable) {
		var result = medicalRecordService.getAllMedicalRecords(username, pageable);
		var response = ApiResponse.success(result, "All Medical records retrieved successfully!");
		var paginatedResponse = PaginatedApiResponse.of(response);
		return paginatedResponse.toResponseEntity();
	}

}
