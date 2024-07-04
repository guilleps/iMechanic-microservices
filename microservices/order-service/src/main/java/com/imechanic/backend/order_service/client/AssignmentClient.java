package com.imechanic.backend.order_service.client;

import com.imechanic.backend.order_service.config.FeignClientConfiguration;
import com.imechanic.backend.order_service.controller.response.OperationDetailsDTOResponse;
import com.imechanic.backend.order_service.controller.response.StepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "principal-service", configuration = FeignClientConfiguration.class)
public interface AssignmentClient {

    @GetMapping("/api/assignments/operationId/{operationId}/mechanicId/{mechanicId}")
    Long getAssignmentIdByOperationIdAndMechanicId(@PathVariable("operationId") Long operationId, @PathVariable("mechanicId") Long mechanicId);

    @GetMapping("/api/assignments/mechanic/{email}/ids")
    List<String> getAssignmentIdsByMechanic(@PathVariable("email") String email);

    @GetMapping("/api/assignments/{assignmentId}/operation/details")
    OperationDetailsDTOResponse getOperationDetailByAssignmentId(@PathVariable("assignmentId") String assignmentId);

    @GetMapping("/api/assignments/{assignmentId}/operation/steps")
    List<StepDTOResponse> getOperationStepsByAssignmentId(@PathVariable("assignmentId") String assignmentId);
}
