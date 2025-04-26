package com.medibuddy.webapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.medibuddy.webapi.entity.Analysis;
import com.medibuddy.webapi.repository.AnalysisRepository;
import com.medibuddy.webapi.shared.ApiResponse;

@Service
public class AnalysisService {

	@Autowired
	private AnalysisRepository analyses;

	public ApiResponse<List<Analysis>> getAllAnalyses(OAuth2User user, Pageable pageable) {
		System.out.println(user.getAttributes());
		return ApiResponse.of(analyses.findAll(pageable), "found all analyses");
	}
	
}
