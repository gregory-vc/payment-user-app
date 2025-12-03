package org.example.exception;

public class NotEnoughBalance extends RuntimeException {
    public NotEnoughBalance(String message) {
        super(message);
    }
}
