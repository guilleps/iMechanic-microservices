package com.imechanic.backend.order_service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTORequest {
    private String workOrderId;
    private String assignmentId;
}
