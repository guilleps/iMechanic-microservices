package com.imechanic.backend.principal_service.exception;

import com.imechanic.backend.principal_service.exception.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(IncorrectCredentials.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDTO> handleCredentialsIncorrect(IncorrectCredentials ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .error("Invalid credentials")
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .date(new Date())
                        .build());
    }

    @ExceptionHandler(TokenNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> handleTokenNotFound(TokenNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .error("Invalid token")
                        .status(HttpStatus.NOT_FOUND.value())
                        .date(new Date())
                        .build());
    }

    @ExceptionHandler(JwtAuthentication.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDTO> handleJwtAuthentication(JwtAuthentication ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .error("Invalid access token")
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .date(new Date())
                        .build());
    }

    @ExceptionHandler(RoleNotAuthorized.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorDTO> handleRoleNotAuthorized(RoleNotAuthorized ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .error("RoleEntity noo autorized")
                        .status(HttpStatus.FORBIDDEN.value())
                        .date(new Date())
                        .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .error("Resource not found or not exist")
                        .status(HttpStatus.NOT_FOUND.value())
                        .date(new Date())
                        .build());
    }
}
