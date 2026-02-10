package com.burykin.auth_api.processing;

import com.burykin.auth_api.processing.dto.ProcessingRequest;
import com.burykin.auth_api.processing.dto.ProcessingResult;
import com.burykin.auth_api.processing.dto.serviceB.ProcessingResponse;
import com.burykin.auth_api.processing.exception.UnauthorizedException;
import com.burykin.auth_api.processing.service.ProcessingService;
import com.burykin.auth_api.processing.service.ProcessingServiceClient;
import com.burykin.auth_api.validation.ValidationMessages;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/process")
public class ProcessingController {
    private final ProcessingServiceClient processingServiceClient;
    private final ProcessingService processingService;
    private final ProcessingMapper processingMapper;

    public ProcessingController(ProcessingServiceClient processingServiceClient, ProcessingService processingService,
                                ProcessingMapper processingMapper) {
        this.processingServiceClient = processingServiceClient;
        this.processingService = processingService;
        this.processingMapper = processingMapper;
    }

    @PostMapping
    public ResponseEntity<ProcessingResult> process(
            @RequestBody ProcessingRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UnauthorizedException();
        }
        String email = auth.getName();
        ProcessingResponse response = processingServiceClient.process(request.text());
        Processing processing = processingService.save(request.text(), response.text(), email);
        return ResponseEntity.ok(processingMapper.toResponse(processing));
    }
}
