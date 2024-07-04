package com.imechanic.backend.order_service.exception;

public class StepCompletedException extends RuntimeException {
    public StepCompletedException(String message) {
        super(message);
    }
}
