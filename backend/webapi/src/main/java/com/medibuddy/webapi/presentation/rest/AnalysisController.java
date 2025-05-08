package com.medibuddy.webapi.presentation.rest;

import com.medibuddy.webapi.entity.Analysis;
import com.medibuddy.webapi.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/analyses")
public class AnalysisController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping
	public ResponseEntity<?> getAllAnalyses(@AuthenticationPrincipal OAuth2User user, Pageable pageable) {
		return analysisService.getAllAnalyses(user, pageable).toResponseEntity();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAnalysisById(@PathVariable UUID id) {
		return analysisService.getAnalysisById(id).toResponseEntity();
	}

	@PostMapping
	public ResponseEntity<?> createAnalysis(@RequestBody Analysis analysis) {
		return analysisService.createAnalysis(analysis).toResponseEntity();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateAnalysis(@PathVariable UUID id, @RequestBody Analysis analysis) {
		return analysisService.updateAnalysis(id, analysis).toResponseEntity();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAnalysis(@PathVariable UUID id) {
		return analysisService.deleteAnalysis(id).toResponseEntity();
	}

	@GetMapping("/type/{type}")
	public ResponseEntity<?> getAnalysesByType(@PathVariable String type, Pageable pageable) {
		return analysisService.getAnalysesByType(type, pageable).toResponseEntity();
	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<?> getAnalysesByPatient(@PathVariable String patientId, Pageable pageable) {
		return analysisService.getAnalysesByPatient(patientId, pageable).toResponseEntity();
	}
}
