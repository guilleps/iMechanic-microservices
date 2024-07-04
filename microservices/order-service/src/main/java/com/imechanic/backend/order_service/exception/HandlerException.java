package com.imechanic.backend.order_service.exception;

import com.imechanic.backend.order_service.exception.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(StepCompletedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDTO> handleStepCompleted(StepCompletedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .error("The step has been completed")
                        .status(HttpStatus.CONFLICT.value())
                        .date(new Date())
                        .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .error("The entity has not been found")
                        .status(HttpStatus.NOT_FOUND.value())
                        .date(new Date())
                        .build());
    }
}
