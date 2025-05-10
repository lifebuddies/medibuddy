package com.medibuddy.webapi.service;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Transactional
@Service
public class AccountService {

    @Value("${ISSUER_URI:http://localhost:9000}")
    private String ISSUER_URI;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserFeedbackRepository userFeedbackRepository;

	@Autowired
	NewsletterSubscriptionRepository newsletterSubscriptionRepository;

	public UserFeedbackResponse submitFeedback(String username, UserFeedbackRequest feedbackRequest) {
		var user = userRepository.findById(username).get();
		var feedback = new UserFeedback(user, feedbackRequest.type(), feedbackRequest.message());
		feedback = userFeedbackRepository.save(feedback);
		return new UserFeedbackResponse(feedback.getId(), feedback.getType(), feedback.getMessage());
	}

	public UserFeedbackResponse getFeedbackById(String username, UUID feedbackId) {
		var feedback = userFeedbackRepository.findById(feedbackId).get();
		return new UserFeedbackResponse(feedback.getId(), feedback.getType(), feedback.getMessage());
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
				.map(feedback -> new UserFeedbackResponse(feedback.getId(), feedback.getType(), feedback.getMessage()));
		return response;
	}

	public RegistrationResponse registerUser(RegistrationRequest registration, RestClient client) {
		return client.post()
             .uri(URI.create(ISSUER_URI + "/api/v1/user"))
             .body(registration)
             .retrieve()
             .body(RegistrationResponse.class);
	}
	
}
