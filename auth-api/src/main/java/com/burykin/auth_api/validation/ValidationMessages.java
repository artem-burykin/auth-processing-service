package com.burykin.auth_api.validation;

public final class ValidationMessages {
    private ValidationMessages() {}

    public static final String EMAIL_NOT_BLANK = "Email must not be blank";
    public static final String EMAIL_PATTERN = "Email must be a valid email address";
    public static final String PASSWORD_NOT_BLANK = "Password must not be blank";
    public static final String PASSWORD_SIZE = "Password must be between 8 and 64 characters";
    public static final String PASSWORD_PATTERN = "Password must contain at least one letter and one number";
    public static final String PROCESSING_INPUT_NOT_NULL = "Input must not be null";
}
