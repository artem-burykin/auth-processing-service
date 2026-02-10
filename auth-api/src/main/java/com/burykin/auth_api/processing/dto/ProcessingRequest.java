package com.burykin.auth_api.processing.dto;

import com.burykin.auth_api.validation.ValidationMessages;
import jakarta.validation.constraints.NotNull;

public record ProcessingRequest(
        @NotNull(message = ValidationMessages.PROCESSING_INPUT_NOT_NULL)
        String text
) {
}
