package org.example.controller;

import org.example.dto.integrations.*;
import org.example.service.IntegrationService;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class IntegrationController {

    private final IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") long id) {
        return integrationService.getUser(id);
    }

    @PostMapping("/product/{id}")
    public ProductDto makePayment(
            @PathVariable("id") long id,
            @RequestParam("amount") BigDecimal amount
    ) {
        return integrationService.makePayment(id, amount);
    }
}
