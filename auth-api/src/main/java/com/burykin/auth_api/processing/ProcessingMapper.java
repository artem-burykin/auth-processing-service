package com.burykin.auth_api.processing;

import com.burykin.auth_api.processing.dto.ProcessingResult;
import org.springframework.stereotype.Component;

@Component
public class ProcessingMapper {
    public ProcessingResult toResponse(Processing processing) {
        return new ProcessingResult(processing.getOutputText());
    }
}
