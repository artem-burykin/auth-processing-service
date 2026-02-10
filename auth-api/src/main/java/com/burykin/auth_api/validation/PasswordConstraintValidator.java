package com.burykin.auth_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    private static final Pattern PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).+$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        return PATTERN.matcher(value).matches();
    }
}
