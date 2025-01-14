package com.glady.company_service.clients;

import com.glady.company_service.requests.DepositRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DepositClient {

    private final WebClient webClient;

    public DepositClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<String> makeDeposit(DepositRequest depositRequest) {
        return webClient.post()
                .uri("/deposit")
                .bodyValue(depositRequest)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
