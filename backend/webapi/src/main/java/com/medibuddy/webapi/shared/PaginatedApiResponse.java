package com.medibuddy.webapi.shared;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class PaginatedApiResponse<T> extends BaseApiResponse<T, PaginatedApiResponse<T>> {

	private long totalPages;
	private long totalElements;
	private long size;
	private List<T> data;
	private long number;
	private Sort sort;
	private long numberOfElements;
	private Pageable pageable;
	private boolean first;
	private boolean last;
	private boolean empty;

	protected PaginatedApiResponse(boolean success, int status, String message, Page<T> page, List<String> errors,
			String path, ZonedDateTime timestamp) {
		super(success, status, message, errors, path, timestamp);
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		this.size = page.getSize();
		this.data = page.getContent();
		this.number = page.getNumber();
		this.sort = page.getSort();
		this.numberOfElements = page.getNumberOfElements();
		this.pageable = page.getPageable();
		this.first = page.isFirst();
		this.last = page.isLast();
		this.empty = page.isEmpty();
	}

	public static <T> PaginatedApiResponse<T> of(ApiResponse<Page<T>> apiResponse) {
		return new PaginatedApiResponse<>(apiResponse.isSuccess(), apiResponse.getStatus(), apiResponse.getMessage(),
				apiResponse.getData(), apiResponse.getErrors(), apiResponse.getPath(), apiResponse.getTimestamp());
	}

}
