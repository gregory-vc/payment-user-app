package org.example.controller.handlers;

import org.example.dto.integrations.IntegrationErrorResponseDto;
import org.example.exception.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(IntegrationException.class)
    public IntegrationErrorResponseDto handleRuntimeException(IntegrationException integrationException) {
        return new IntegrationErrorResponseDto(integrationException.getLocalReason(), integrationException.getMessage());
    }
}
