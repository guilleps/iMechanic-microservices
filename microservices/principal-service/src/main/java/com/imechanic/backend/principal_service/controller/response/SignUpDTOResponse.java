package com.imechanic.backend.principal_service.controller.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message"})
public record SignUpDTOResponse(String message) {
}