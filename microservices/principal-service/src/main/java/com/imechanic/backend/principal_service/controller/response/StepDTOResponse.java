package com.imechanic.backend.principal_service.controller.response;

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

    public StepDTOResponse(Long stepId, String name) {
        this.stepId = stepId;
        this.name = name;
    }
}

