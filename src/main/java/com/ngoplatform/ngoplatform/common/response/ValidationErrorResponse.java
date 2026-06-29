package com.ngoplatform.ngoplatform.common.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ValidationErrorResponse extends ErrorResponse {

    private final Map<String, String> errors;

    public ValidationErrorResponse(
            LocalDateTime timestamp,
            int status,
            String error,
            String message,
            Map<String, String> errors) {

        super(timestamp, status, error, message);
        this.errors = errors;
    }
}