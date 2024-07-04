package com.imechanic.backend.principal_service.exception.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record ErrorDTO(String message, String error, int status, Date date) {
}

