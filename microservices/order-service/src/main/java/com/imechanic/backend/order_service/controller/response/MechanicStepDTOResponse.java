package com.imechanic.backend.order_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanicStepDTOResponse {
    private String workOrderId;
    private Long mechanicId;
    private Long operationId;
    private String operationName;
    private Long stepId;
    private boolean complete;
}
