package com.burykin.auth_api.auth.dto;

import com.burykin.auth_api.validation.ValidPassword;
import com.burykin.auth_api.validation.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUser(
        @NotBlank(message = ValidationMessages.EMAIL_NOT_BLANK)
        @Email(message = ValidationMessages.EMAIL_PATTERN)
        String email,
        @NotBlank(message = ValidationMessages.PASSWORD_NOT_BLANK)
        @Size(min = 8, max = 64, message = ValidationMessages.PASSWORD_SIZE)
        @ValidPassword
        String password
) {
}
