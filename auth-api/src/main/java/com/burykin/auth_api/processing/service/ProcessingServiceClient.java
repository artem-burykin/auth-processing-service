package com.burykin.auth_api.processing.service;

import com.burykin.auth_api.processing.dto.serviceB.ProcessingRequest;
import com.burykin.auth_api.processing.dto.serviceB.ProcessingResponse;
import com.burykin.auth_api.processing.exception.ServiceBException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProcessingServiceClient {
    private final WebClient webClient;

    public ProcessingServiceClient(@Qualifier("serviceBClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public ProcessingResponse process(String text) {
        ProcessingRequest request = new ProcessingRequest(text);
        return webClient
                .post()
                .uri("/api/transform")
                .bodyValue(request) // JSON объект
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .map(ServiceBException::new))
                .bodyToMono(ProcessingResponse.class)
                .block();
    }
}
