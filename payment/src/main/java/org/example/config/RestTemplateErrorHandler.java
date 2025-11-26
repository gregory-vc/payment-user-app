package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.integrations.ExecutorErrorResponseDto;
import org.example.exception.IntegrationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    public RestTemplateErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            ExecutorErrorResponseDto executorErrorResponseDto = objectMapper.readValue(response.getBody(), ExecutorErrorResponseDto.class);
            throw new IntegrationException("Внешний сервис вернул ошибку", executorErrorResponseDto.message());
        }
    }
}
