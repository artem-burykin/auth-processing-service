package com.burykin.auth_api.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ValidationErrorExtractor {
    public record ValidationErrors(
            Map<String, String> fieldErrors,
            List<String> globalErrors
    ) {
    }

    public ValidationErrors extract(final List<? extends MessageSourceResolvable> resolvables) {
        final Map<String, String> fieldErrors = new HashMap<>();
        final List<String> globalErrors = new ArrayList<>();

        for (MessageSourceResolvable error : resolvables) {
            String message = error.getDefaultMessage();
            if (error instanceof FieldError fe) {
                fieldErrors.put(fe.getField(), message);
            } else {
                globalErrors.add(message);
            }
        }

        return new ValidationErrors(fieldErrors.isEmpty() ? null : fieldErrors,
                globalErrors.isEmpty() ? null : globalErrors);
    }

}
