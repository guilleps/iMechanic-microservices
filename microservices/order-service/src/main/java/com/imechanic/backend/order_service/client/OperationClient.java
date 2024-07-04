package com.imechanic.backend.order_service.client;

import com.imechanic.backend.order_service.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "principal-service", configuration = FeignClientConfiguration.class)
public interface OperationClient {

    @GetMapping("/api/operations/{assignmentId}/attribute/{attribute}")
    String getOperationAttributeByAssignmentIdAndAttribute(@PathVariable("assignmentId") String assignmentId, @PathVariable("attribute") String attribute);

    @PostMapping("/api/operations/{assignmentId}/operation/in/process")
    void setOperationStatusToInProcessByAssignmentId(@PathVariable("assignmentId") String assignmentId);

    @PostMapping("/api/operations/{assignmentId}/operation/to/finalized")
    void setOperationStatusToFinalizedByAssignmentId(@PathVariable("assignmentId") String assignmentId);
}
