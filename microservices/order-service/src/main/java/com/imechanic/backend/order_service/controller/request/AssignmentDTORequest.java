package com.imechanic.backend.order_service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTORequest {
    private Long operationId;
    private Long mechanicId;
}