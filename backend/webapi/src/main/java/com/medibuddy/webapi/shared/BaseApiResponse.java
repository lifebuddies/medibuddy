package com.medibuddy.webapi.shared;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public abstract class BaseApiResponse<T, SELF extends BaseApiResponse<T, SELF>> {

	protected boolean success;
	protected int status;
	protected String message;
	protected List<String> errors;
	protected String path;
	protected ZonedDateTime timestamp;

	protected BaseApiResponse(boolean success, int status, String message, List<String> errors, String path,
			ZonedDateTime timestamp) {
		this.success = success;
		this.status = status;
		this.message = message;
		this.errors = errors;
		this.path = path;
		this.timestamp = timestamp;
	}

	@SuppressWarnings("unchecked")
	protected SELF self() {
		return (SELF) this;
	}

	public ResponseEntity<SELF> toResponseEntity() {
		return ResponseEntity.status(status).body(self());
	}

	protected static String getRequestPath() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs == null) {
			throw new IllegalStateException(
					"No request attributes found. This method must be called in the context of an HTTP request.");
		}
		return attrs.getRequest().getRequestURI();
	}

}
