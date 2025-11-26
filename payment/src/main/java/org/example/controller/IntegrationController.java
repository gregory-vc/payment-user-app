package org.example.controller;

import org.example.dto.integrations.ExecutorErrorResponseDto;
import org.example.dto.integrations.ExecutorResponseDto;
import org.example.dto.integrations.IntegrationErrorResponseDto;
import org.example.service.IntegrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/integrations")
public class IntegrationController {

    private final IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping("executor/payments/execute")
    public ExecutorResponseDto executorHealthCheck() {
        return integrationService.executePayment();
    }

    @GetMapping("executor/payments/execute/500")
    public ExecutorResponseDto executorHealthCheck500() {
        return integrationService.executePaymentError500();
    }
}
