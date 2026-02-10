package com.burykin.auth_api.exception;

public final class ExceptionMessages {
    private ExceptionMessages() {}

    public static final String UNEXPECTED_EXCEPTION = "Unexpected error occurred";
    public static final String VALIDATION_FAILED = "Validation failed";
    public static final String AUTHENTICATION_FAILED = "Authentication failed";
    public static final String USER_FROM_JWT_NOT_FOUND_IN_DB = "User from jwt not found in db";
    public static final String JWT_KEY_IS_TOO_SHORT = "JWT secret too short, must be at least 32 bytes";
    public static final String USER_NOT_AUTHENTICATED = "User not authenticated";
}
