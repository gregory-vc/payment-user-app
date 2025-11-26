package org.example.service;

import org.example.dto.integrations.ExecutorErrorResponseDto;
import org.example.dto.integrations.ExecutorResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IntegrationService {

    private final RestTemplate restTemplate;

    @Value("${integrations.clients.payments-executor-client.execute-url}")
    private String executePaymentMethodUrl;

    @Value("${integrations.clients.payments-executor-client.execute-url-500}")
    private String executePaymentMethodUrl500;

    public IntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExecutorResponseDto executePayment() {
        return restTemplate.postForObject(
                executePaymentMethodUrl,
                null,
                ExecutorResponseDto.class
        );
    }

    public ExecutorResponseDto executePaymentError500() {
        return restTemplate.postForObject(
                executePaymentMethodUrl500,
                null,
                ExecutorResponseDto.class
        );
    }
}
