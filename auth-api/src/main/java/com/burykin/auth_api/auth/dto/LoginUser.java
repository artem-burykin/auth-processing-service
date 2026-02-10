package com.burykin.auth_api.auth.dto;

import com.burykin.auth_api.validation.ValidationMessages;
import jakarta.validation.constraints.NotBlank;

public record LoginUser(
        @NotBlank(message = ValidationMessages.EMAIL_NOT_BLANK)
        String email,
        @NotBlank(message = ValidationMessages.PASSWORD_NOT_BLANK)
        String password
) {
}
