package com.burykin.data_api.processing;

import com.burykin.data_api.processing.dto.ProcessingRequest;
import com.burykin.data_api.processing.dto.ProcessingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transform")
public class ProcessingController {
    @PostMapping
    public ResponseEntity<ProcessingResponse> process(@RequestBody ProcessingRequest request) {
        ProcessingResponse response = new ProcessingResponse(request.text().toUpperCase());
        return ResponseEntity.ok(response);
    }
}
