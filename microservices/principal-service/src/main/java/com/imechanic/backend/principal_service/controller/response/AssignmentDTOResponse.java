package com.imechanic.backend.principal_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDTOResponse {
    private Long operationId;
    private Long mechanicId;
}