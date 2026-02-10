package com.burykin.auth_api.auth.exception;

import com.burykin.auth_api.exception.ExceptionMessages;

public final class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super(ExceptionMessages.AUTHENTICATION_FAILED);
    }
}
