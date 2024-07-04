package com.imechanic.backend.order_service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderDTORequest {
    private String emailCustomer;
    private String plate;
    private List<AssignmentDTORequest> assignmentDTORequests;
}
