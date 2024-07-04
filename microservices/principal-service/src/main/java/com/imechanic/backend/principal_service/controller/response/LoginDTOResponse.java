package com.imechanic.backend.principal_service.controller.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "token", "role"})
public record LoginDTOResponse(String message,
                               String token,
                               String role) {
}
