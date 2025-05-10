package com.medibuddy.webapi.presentation.rest.v1.dto.account;

import java.util.UUID;

import com.medibuddy.webapi.entity.account.UserFeedback.FeedbackType;

public record UserFeedbackResponse(UUID id, FeedbackType type, String message)  {

}
