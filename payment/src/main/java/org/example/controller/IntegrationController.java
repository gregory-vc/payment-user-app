package org.example.controller;

import org.example.dto.integrations.*;
import org.example.service.IntegrationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @PostMapping("/payment/{id}")
    public ProductDto makePayment(@PathVariable("id") long id) {
        return integrationService.makePayment(id);
    }
}
