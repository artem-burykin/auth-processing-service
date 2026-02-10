package com.burykin.auth_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient serviceBClient(@Value("${service.service-b-url}") String baseUrl,
                                    @Value("${service.internal-token}") String internalToken) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-Internal-Token", internalToken)
                .build();
    }
}
