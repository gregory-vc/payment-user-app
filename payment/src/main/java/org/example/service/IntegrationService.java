package org.example.service;

import org.example.dto.integrations.ProductDto;
import org.example.dto.integrations.UserDto;
import org.example.exception.IntegrationException;
import org.example.exception.NotEnoughBalance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

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

    public ProductDto makePayment(Long productId, BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше нуля");
        }

        ProductDto productDto = restTemplate.getForObject(
                productUrl + "/{id}",
                ProductDto.class,
                productId
        );

        if (productDto == null) {
            throw new IntegrationException(
                    "Не удалось получить продукт",
                    "Сервис продуктов вернул пустой ответ"
            );
        }

        BigDecimal balance = productDto.balance();
        if (balance == null) {
            throw new IntegrationException(
                    "Не удалось получить баланс продукта",
                    "В ответе сервиса продуктов нет баланса"
            );
        }

        if (balance.compareTo(amount) < 0) {
            throw new NotEnoughBalance("На балансе недостаточно денег");
        }

        return productDto;
    }
}
