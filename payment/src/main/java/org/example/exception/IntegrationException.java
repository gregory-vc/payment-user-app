package org.example.exception;

public class IntegrationException extends RuntimeException {

    private final String localReason;

    public IntegrationException(String localReason, String externalMessage) {
        super(externalMessage);
        this.localReason = localReason;
    }

    public String getLocalReason() {
        return localReason;
    }
}
