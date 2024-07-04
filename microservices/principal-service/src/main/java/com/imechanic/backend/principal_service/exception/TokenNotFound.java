package com.imechanic.backend.principal_service.exception;

public class TokenNotFound extends RuntimeException {
    public TokenNotFound(String message) {
        super(message);
    }
}
