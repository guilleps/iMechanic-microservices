package com.imechanic.backend.order_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDetailsDTOResponse {
    private OperationDTOResponse operation;
    private MechanicDTOResponse mechanic;
    private String statusOperation;
    private List<StepDTOResponse> steps;
}
