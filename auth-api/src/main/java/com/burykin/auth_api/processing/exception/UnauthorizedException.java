package com.burykin.auth_api.processing.exception;

import com.burykin.auth_api.exception.ExceptionMessages;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super(ExceptionMessages.USER_NOT_AUTHENTICATED);
    }
}
