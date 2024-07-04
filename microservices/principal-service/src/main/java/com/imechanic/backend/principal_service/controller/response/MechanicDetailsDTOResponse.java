package com.imechanic.backend.principal_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanicDetailsDTOResponse {
    private String name;
    private String phone;
    private String email;
    private List<OperationDTOResponse> operations;
}
