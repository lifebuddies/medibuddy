package com.medibuddy.webapi.shared;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiResponse<T> extends BaseApiResponse<T, ApiResponse<T>> {

	protected T data;

	protected ApiResponse(boolean success, int status, String message, T data, List<String> errors, String path,
			ZonedDateTime timestamp) {
		super(success, status, message, errors, path, timestamp);
		this.data = data;
	}

	public static <T> ApiResponse<T> success(T data, String message) {
		return success(data, message, HttpStatus.OK);
	}

	public static <T> ApiResponse<T> success(T data, String message, HttpStatus status) {
		return success(data, message, status.value());
	}

	public static <T> ApiResponse<T> success(T data, String message, int status) {
		return new ApiResponse<>(
				true,
				status,
				message,
				data,
				List.of(),
				getRequestPath(),
				ZonedDateTime.now(ZoneOffset.UTC));
	}

	public static <T> ApiResponse<T> error(String error, int status) {
		return error(error, HttpStatus.valueOf(status).getReasonPhrase(), status);
	}

	public static <T> ApiResponse<T> error(String error, HttpStatus status) {
		return error(error, status.getReasonPhrase(), status.value());
	}

	public static <T> ApiResponse<T> error(String error, String message, int status) {
		return error(List.of(error), message, status);
	}

	public static <T> ApiResponse<T> error(List<String> errors, int status) {
		return error(errors, HttpStatus.valueOf(status).getReasonPhrase(), status);
	}

	public static <T> ApiResponse<T> error(List<String> errors, HttpStatus status) {
		return error(errors, status.getReasonPhrase(), status.value());
	}

	public static <T> ApiResponse<T> error(List<String> errors, String message, int status) {
		return new ApiResponse<>(
				false,
				status,
				message,
				null,
				errors,
				getRequestPath(),
				ZonedDateTime.now(ZoneOffset.UTC));
	}

}
