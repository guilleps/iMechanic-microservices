package com.imechanic.backend.principal_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTOResponse {
    private Long id;
    private String name;
}
