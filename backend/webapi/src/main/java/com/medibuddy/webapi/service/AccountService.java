package com.medibuddy.webapi.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.medibuddy.webapi.entity.account.User;
import com.medibuddy.webapi.entity.account.UserFeedback;
import com.medibuddy.webapi.entity.account.NewsletterSubscription;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.RegistrationRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.FeedbackRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.account.NewsletterSubscriptionRequest;
import com.medibuddy.webapi.shared.ApiResponse;

public interface AccountService {
    ApiResponse<User> registerUser(RegistrationRequest request);
    ApiResponse<UserFeedback> submitFeedback(FeedbackRequest request);
    ApiResponse<Page<UserFeedback>> getAllFeedbacks(Pageable pageable);
    ApiResponse<UserFeedback> getFeedbackById(UUID feedbackId);
    ApiResponse<NewsletterSubscription> subscribeToNewsletter(NewsletterSubscriptionRequest request);
} 