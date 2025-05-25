package com.medibuddy.webapi.presentation.rest.v1.dto.account;

import com.medibuddy.webapi.entity.account.UserFeedback.FeedbackType;

import jakarta.validation.constraints.NotBlank;

public record UserFeedbackRequest(FeedbackType type, int starCount, @NotBlank String message) {

}
