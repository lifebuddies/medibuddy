package com.medibuddy.webapi.presentation.rest.v1.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.medibuddy.webapi.presentation.rest.v1.dto.account.FeedbackRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.NewsletterSubscriptionRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.RegistrationRequest;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

	@PostMapping("/registration")
	public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registration) {
		// TODO: Implement the registration logic
		return ResponseEntity.ok("Registration successful!");
	}

	@PostMapping("/feedback")
	public ResponseEntity<?> submitFeedback(@RequestBody FeedbackRequest feedback) {
		// TODO: Implement the feedback submission logic
	 	return ResponseEntity.ok("Feedback submitted successfully!");
	}

	@GetMapping("/feedback")
	public ResponseEntity<?> retrieveAllFeedbacks(Pageable pageable) {
		// TODO: Implement the logic to retrieve all feedback
	 	return ResponseEntity.ok("Feedbacks retrieved successfully!");
	}

	@GetMapping("/feedback/{feedbackId}")
	public ResponseEntity<?> retrieveFeedbackById(@PathVariable UUID feedbackId) {
		// TODO: Implement the logic to retrieve feedback by ID
		return ResponseEntity.ok("Feedback retrieved successfully!");
	}



}
