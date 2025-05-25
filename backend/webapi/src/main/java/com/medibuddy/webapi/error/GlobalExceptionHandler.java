package com.medibuddy.webapi.error;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.medibuddy.webapi.shared.ApiResponse;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<String> handleAll(Exception ex) {
        log.error("Unhandled exception: ", ex); // Log for devs only
        return ApiResponse.error(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Illegal argument: ", ex); // Log for devs only
        return ApiResponse.error(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("Invalid request data: ", ex); // Log for devs only
        return ApiResponse.error(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleConstraintViolation(ConstraintViolationException ex) {
        log.error("Error validating request: ", ex); // Log for devs only
        return ApiResponse.error(
                ex.getConstraintViolations().stream()
                        .map((cv) -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage()).toList(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<String> handleNoSuchElement(NoSuchElementException ex) {
        log.error("Cannot find such element: ", ex); // Log for devs only
        return ApiResponse.error(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<String> handleAccessDenied(AccessDeniedException ex) {
        log.error("Access Denied: ", ex); // Log for devs only
        return ApiResponse.error(ex.getLocalizedMessage(), HttpStatus.FORBIDDEN);
    }

}
