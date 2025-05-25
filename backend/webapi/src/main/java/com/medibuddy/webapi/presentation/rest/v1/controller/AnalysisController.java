package com.medibuddy.webapi.presentation.rest.v1.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.medibuddy.webapi.presentation.rest.v1.dto.ai.MlModelInputColumnDto;
import com.medibuddy.webapi.presentation.rest.v1.dto.analysis.AnalysisRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.analysis.AnalysisResponse;
import com.medibuddy.webapi.presentation.rest.v1.dto.analysis.DiagnosisResponse;
import com.medibuddy.webapi.service.AnalysisService;
import com.medibuddy.webapi.shared.ApiResponse;

import ai.onnxruntime.OrtException;

@RestController
@RequestMapping("/api/v1/analyses")
public class AnalysisController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/types")
	public ResponseEntity<ApiResponse<List<String>>> getAnalysisTypes(
			@RequestParam(defaultValue = "false") Boolean availableOnly) {
		var result = analysisService.getAnalysisTypes(availableOnly);
		var response = ApiResponse.success(result, "Analysis types retrieved successfully!");
		return response.toResponseEntity();
	}

	@GetMapping("/{typeName}/inputs")
	public ResponseEntity<ApiResponse<List<MlModelInputColumnDto>>> getAnalysisInputDetails(
			@PathVariable String typeName,
			@RequestParam(required = false, defaultValue = "false") Boolean required) {
		var result = analysisService.getAnalysisInputDetails(typeName, required);
		var response = ApiResponse.success(result, "Analysis input details retrieved successfully!");
		return response.toResponseEntity();
	}

	@PreAuthorize("hasAuthority('SCOPE_analysis.write')")
	@PostMapping("/{typeName}/requests")
	public ResponseEntity<ApiResponse<DiagnosisResponse>> requestAnalysis(
			Principal user,
			@PathVariable String typeName,
			@RequestBody AnalysisRequest request) throws OrtException {
		var username = user.getName();
		var result = analysisService.requestAnalysis(username, typeName, request);
		var response = ApiResponse.success(result, "Analysis requested successfully!");
		return response.toResponseEntity();
	}

	@PreAuthorize("hasAuthority('SCOPE_analysis.read')")
	@GetMapping("/requests/{requestId}")
	public ResponseEntity<ApiResponse<AnalysisResponse>> getAnalysisRequestById(
			Principal user,
			@PathVariable UUID requestId) {
		var username = user.getName();
		var result = analysisService.getAnalysisRequestById(username, requestId);
		var response = ApiResponse.success(result, "Analysis request details retrieved successfully!");
		return response.toResponseEntity();
	}

	@PreAuthorize("hasAuthority('SCOPE_analysis.read')")
	@GetMapping("/requests/{requestId}/diagnosis")
	public ResponseEntity<ApiResponse<DiagnosisResponse>> getDiagnosisByAnalysisId(
			Principal user,
			@PathVariable UUID requestId) {
		var username = user.getName();
		var result = analysisService.getDiagnosisByAnalysisId(username, requestId);
		var response = ApiResponse.success(result, "Diagnosis result retrieved successfully!");
		return response.toResponseEntity();
	}

}
