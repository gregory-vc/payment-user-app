package org.example.controller.handlers;

import org.example.dto.integrations.IntegrationErrorResponseDto;
import org.example.exception.IntegrationException;
import org.example.exception.NotEnoughBalance;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public IntegrationErrorResponseDto handleMissingException(MissingServletRequestParameterException missingServletRequestParameterException) {
        return new IntegrationErrorResponseDto("Ошибка запроса", missingServletRequestParameterException.getMessage());
    }

    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    @ExceptionHandler(NotEnoughBalance.class)
    public IntegrationErrorResponseDto handleBalanceException(NotEnoughBalance notEnoughBalance) {
        return new IntegrationErrorResponseDto("Ошибка баланса", notEnoughBalance.getMessage());
    }
}
