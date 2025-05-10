package com.medibuddy.webapi.presentation.rest.v1.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.*;
import com.medibuddy.webapi.service.AccountService;
import com.medibuddy.webapi.shared.ApiResponse;
import com.medibuddy.webapi.shared.PaginatedApiResponse;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RestClient client;

	@PostMapping("/registration")
	public ResponseEntity<ApiResponse<RegistrationResponse>> registerUser(
			@RequestBody RegistrationRequest registration) {
		var result = accountService.registerUser(registration, client);
		var response = ApiResponse.success(result, "Registration successful!", HttpStatus.CREATED);
		return response.toResponseEntity();
	}

	// @PreAuthorize("hasAuthority('SCOPE_feedback.write')")
	@PostMapping("/feedback")
	public ResponseEntity<ApiResponse<UserFeedbackResponse>> submitFeedback(
			@AuthenticationPrincipal(expression = "attributes['sub']") String username,
			@RequestBody UserFeedbackRequest feedback) {
		var result = accountService.submitFeedback(username, feedback);
		var response = ApiResponse.success(result, "Feedback submitted successfully!", HttpStatus.CREATED);
		return response.toResponseEntity();
	}

	// @PreAuthorize("hasAuthority('SCOPE_feedback.read')")
	@GetMapping("/feedback")
	public ResponseEntity<PaginatedApiResponse<UserFeedbackResponse>> getAllFeedbacks(
			@AuthenticationPrincipal(expression = "attributes['sub']") String username,
			Pageable pageable) {
		var result = accountService.getAllFeedbacks(username, pageable);
		var response = ApiResponse.success(result, "Feedbacks retrieved successfully!");
		var paginatedReponse = PaginatedApiResponse.of(response);
		return paginatedReponse.toResponseEntity();
	}

	// @PreAuthorize("hasAuthority('SCOPE_feedback.read')")
	@GetMapping("/feedback/{feedbackId}")
	public ResponseEntity<ApiResponse<UserFeedbackResponse>> getFeedbackById(
			@AuthenticationPrincipal(expression = "attributes['sub']") String username,
			@PathVariable UUID feedbackId) {
		var result = accountService.getFeedbackById(username, feedbackId);
		var response = ApiResponse.success(result, "Feedback retrieved successfully!");
		return response.toResponseEntity();
	}

	@PostMapping("/newsletter-subscription")
	public ResponseEntity<ApiResponse<NewsletterSubscriptionResponse>> subscribeToNewsletter(
			@RequestBody NewsletterSubscriptionRequest subscription) {
		var result = accountService.subscribeToNewsletter(subscription);
		var response = ApiResponse.success(result, "Subscribed to newsletter successfully!");
		return response.toResponseEntity();
	}

}
