package com.medibuddy.webapi.presentation.rest.v1.dto.account;

import jakarta.validation.constraints.NotBlank;

public record NewsletterSubscriptionRequest(
	@NotBlank String email
)  {

}
