package com.imechanic.backend.order_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepDTOResponse {
    private Long stepId;
    private String name;
    private int order;
    private boolean complete;
}
