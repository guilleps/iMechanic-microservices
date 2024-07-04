package com.imechanic.backend.principal_service.exception;

public class JwtAuthentication extends RuntimeException {
    public JwtAuthentication(String message) {
        super(message);
    }
}
