package com.medibuddy.webapi.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medibuddy.webapi.service.AnalysisService;

@RestController
@RequestMapping("/api/analyses")
public class AnalysisController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping
	public ResponseEntity<?> getAllAnalyses(@AuthenticationPrincipal OAuth2User user, Pageable pageable) {
		return analysisService.getAllAnalyses(user, pageable).toResponseEntity();
	}

}
