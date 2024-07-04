package com.imechanic.backend.order_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanicWorkOrderDetailsDTOResponse {
    private String id;
    private String name;
    private String address;
    private String phoneWorkshop;
    private OperationDTOResponse operation;
    private String statusOperation;
    private MechanicDTOResponse mechanic;
    private String phoneMechanic;
    private List<StepDTOResponse> steps;
}
