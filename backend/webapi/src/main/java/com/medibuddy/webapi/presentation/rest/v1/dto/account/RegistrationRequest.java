package com.medibuddy.webapi.presentation.rest.v1.dto.account;

import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(
	@NotBlank String username,
	@NotBlank String password,
	@NotBlank String email
)  {

}
