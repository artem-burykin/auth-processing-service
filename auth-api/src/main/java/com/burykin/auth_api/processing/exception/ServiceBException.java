package com.burykin.auth_api.processing.exception;

public class ServiceBException extends RuntimeException {
    private static final String MESSAGE = "Service B error: %s";

    public ServiceBException(String body) {
        super(String.format(MESSAGE, body));
    }
}
