package com.medibuddy.webapi.presentation.rest.v1.dto.account;

import com.medibuddy.webapi.entity.account.UserFeedback.FeedbackType;

import jakarta.validation.constraints.NotBlank;

public record FeedbackRequest(FeedbackType type, @NotBlank String message) {

}
