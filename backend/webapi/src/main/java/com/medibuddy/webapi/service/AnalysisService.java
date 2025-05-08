package com.medibuddy.webapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.medibuddy.webapi.entity.Analysis;
import com.medibuddy.webapi.repository.AnalysisRepository;
import com.medibuddy.webapi.shared.ApiResponse;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AnalysisService {

	@Autowired
	private AnalysisRepository analyses;

	public ApiResponse<Page<Analysis>> getAllAnalyses(OAuth2User user, Pageable pageable) {
		System.out.println(user.getAttributes());
		return ApiResponse.success(analyses.findAll(pageable), "found all analyses");
	}

	public ApiResponse<Analysis> getAnalysisById(UUID id) {
		return ApiResponse.success(
			analyses.findById(id).orElseThrow(() -> new EntityNotFoundException("Analysis not found with id: " + id)),
			"found analysis by id"
		);
	}

	public ApiResponse<Analysis> createAnalysis(Analysis analysis) {
		return ApiResponse.success(analyses.save(analysis), "created new analysis");
	}

	public ApiResponse<Analysis> updateAnalysis(UUID id, Analysis analysis) {
		if (!analyses.existsById(id)) {
			throw new EntityNotFoundException("Analysis not found with id: " + id);
		}
		analysis.setId(id);
		return ApiResponse.success(analyses.save(analysis), "updated analysis");
	}

	public ApiResponse<Void> deleteAnalysis(UUID id) {
		if (!analyses.existsById(id)) {
			throw new EntityNotFoundException("Analysis not found with id: " + id);
		}
		analyses.deleteById(id);
		return ApiResponse.success(null, "deleted analysis");
	}

	public ApiResponse<Page<Analysis>> getAnalysesByType(String type, Pageable pageable) {
		return ApiResponse.success(analyses.findByType(type, pageable), "found analyses by type");
	}

	public ApiResponse<Page<Analysis>> getAnalysesByPatient(String patientId, Pageable pageable) {
		return ApiResponse.success(analyses.findByPatient(patientId, pageable), "found analyses by patient");
	}
}
