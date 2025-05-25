package com.medibuddy.webapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;

import com.medibuddy.webapi.entity.account.NewsletterSubscription;
import com.medibuddy.webapi.entity.account.UserFeedback;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.NewsletterSubscriptionRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.NewsletterSubscriptionResponse;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.RegistrationRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.RegistrationResponse;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.UserFeedbackRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.UserFeedbackResponse;
import com.medibuddy.webapi.repository.account.NewsletterSubscriptionRepository;
import com.medibuddy.webapi.repository.account.UserFeedbackRepository;
import com.medibuddy.webapi.repository.account.UserRepository;

@Validated
@Transactional
@Service
public class AccountService {

	@Value("${ISSUER_URI:http://localhost:9000}")
	private String ISSUER_URI;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailsManager userDetailsManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserFeedbackRepository userFeedbackRepository;

	@Autowired
	NewsletterSubscriptionRepository newsletterSubscriptionRepository;

	public UserFeedbackResponse submitFeedback(String username, UserFeedbackRequest feedbackRequest) {
		var user = userRepository.findById(username).get();
		var feedback = new UserFeedback(user, feedbackRequest.type(), feedbackRequest.starCount(),
				feedbackRequest.message());
		feedback = userFeedbackRepository.save(feedback);
		return new UserFeedbackResponse(feedback.getId(), feedback.getType(), feedback.getStarCount(),
				feedback.getMessage(), feedback.getCreatedAt());
	}

	public UserFeedbackResponse getFeedbackById(String username, UUID feedbackId) {
		var feedback = userFeedbackRepository.findById(feedbackId).get();
		return new UserFeedbackResponse(feedback.getId(), feedback.getType(), feedback.getStarCount(),
				feedback.getMessage(), feedback.getCreatedAt());
	}

	public NewsletterSubscriptionResponse subscribeToNewsletter(NewsletterSubscriptionRequest request) {
		var existingSubscription = userRepository.findByEmail(request.email());
		var subscription = new NewsletterSubscription(existingSubscription.orElse(null), request.email());
		subscription = newsletterSubscriptionRepository.save(subscription);
		return new NewsletterSubscriptionResponse(subscription.getId(), subscription.getEmail());
	}

	public Page<UserFeedbackResponse> getAllFeedbacks(String username, Pageable pageable) {
		var feedbacks = userFeedbackRepository.findByUsername(username, pageable);
		var response = feedbacks
				.map(feedback -> new UserFeedbackResponse(feedback.getId(), feedback.getType(), feedback.getStarCount(),
						feedback.getMessage(), feedback.getCreatedAt()));
		return response;
	}

	public RegistrationResponse registerUser(RegistrationRequest registration, RestClient client) {
		userDetailsManager.createUser(User.withUsername(registration.username())
				.password(passwordEncoder.encode(registration.password())).roles("USER").build());
		var user = userDetailsManager.loadUserByUsername(registration.username());
		return new RegistrationResponse(user != null);
	}

}
