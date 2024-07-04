package com.imechanic.backend.principal_service.exception;

public class RoleNotAuthorized extends RuntimeException {
    public RoleNotAuthorized(String message) {
        super(message);
    }
}

