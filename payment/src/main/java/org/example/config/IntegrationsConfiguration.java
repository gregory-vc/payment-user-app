package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(RestClientConfigurationProperties.class)
public class IntegrationsConfiguration {

    private final RestClientConfigurationProperties restClientConfigurationProperties;

    public IntegrationsConfiguration(RestClientConfigurationProperties restClientConfigurationProperties) {
        this.restClientConfigurationProperties = restClientConfigurationProperties;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateErrorHandler restTemplateErrorHandler) {
        RestTemplateProperties paymentsExecutorClient = restClientConfigurationProperties.getPaymentExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(paymentsExecutorClient.getUrl())
                .connectTimeout(paymentsExecutorClient.getConnectTimeout())
                .readTimeout(paymentsExecutorClient.getReadTimeout())
                .errorHandler(restTemplateErrorHandler)
                .build();
    }
}
