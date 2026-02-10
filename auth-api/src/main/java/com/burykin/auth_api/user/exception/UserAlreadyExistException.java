package com.burykin.auth_api.user.exception;

public class UserAlreadyExistException extends RuntimeException {
    private static final String ERROR_MESSAGE = "User with email %s already exists";

    public UserAlreadyExistException(String email) {
        super(String.format(ERROR_MESSAGE, email));
    }
}
