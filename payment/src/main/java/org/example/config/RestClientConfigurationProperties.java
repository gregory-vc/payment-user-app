package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.clients")
public class RestClientConfigurationProperties {

    private final RestTemplateProperties paymentsExecutorClient;

    public RestClientConfigurationProperties(RestTemplateProperties paymentsExecutorClient) {
        this.paymentsExecutorClient = paymentsExecutorClient;
    }

    public RestTemplateProperties getPaymentExecutorClient() {
        return paymentsExecutorClient;
    }
}
