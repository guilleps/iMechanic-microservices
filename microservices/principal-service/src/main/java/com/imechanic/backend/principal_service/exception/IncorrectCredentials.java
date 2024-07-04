package com.imechanic.backend.principal_service.exception;

public class IncorrectCredentials extends RuntimeException {
    public IncorrectCredentials(String message) {
        super(message);
    }
}