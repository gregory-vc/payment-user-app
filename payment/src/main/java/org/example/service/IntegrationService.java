package org.example.service;

import org.example.dto.integrations.ProductDto;
import org.example.dto.integrations.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IntegrationService {

    private final RestTemplate restTemplate;

    @Value("${integrations.clients.payments-executor-client.user-url}")
    private String userUrl;

    @Value("${integrations.clients.payments-executor-client.product-url}")
    private String productUrl;

    public IntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto getUser(Long userId) {
        return restTemplate.getForObject(
                userUrl + "/{id}",
                UserDto.class,
                userId
        );
    }

    public ProductDto makePayment(Long productId) {
        return restTemplate.getForObject(
                productUrl + "/{id}",
                ProductDto.class,
                productId
        );
    }
}
