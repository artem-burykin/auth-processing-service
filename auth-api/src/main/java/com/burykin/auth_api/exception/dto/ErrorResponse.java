package com.burykin.auth_api.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ErrorResponse {
    private final int status;
    private final String message;
    private final String error;
    private final Instant timestamp;
    private final String path;
    private final Map<String, String> errors;
    private final List<String> globalErrors;

    private ErrorResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.error = builder.error;
        this.path = builder.path;
        this.errors = builder.errors;
        this.globalErrors = builder.globalErrors;
        this.timestamp = Instant.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public static Builder builder(int status, String message, String error, String path) {
        return new Builder(status, message, error, path);
    }

    public static final class Builder {
        private final int status;
        private final String message;
        private final String error;
        private final String path;
        private Map<String, String> errors;
        private List<String> globalErrors;

        private Builder(int status, String message, String error, String path) {
            this.status = status;
            this.message = message;
            this.error = error;
            this.path = path;
        }

        public Builder errors(Map<String, String> errors) {
            this.errors = errors;
            return this;
        }

        public Builder globalErrors(List<String> globalErrors) {
            this.globalErrors = globalErrors;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
