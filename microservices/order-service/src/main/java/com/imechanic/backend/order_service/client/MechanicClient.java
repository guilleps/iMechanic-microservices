package com.imechanic.backend.order_service.client;

import com.imechanic.backend.order_service.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "principal-service", configuration = FeignClientConfiguration.class)
public interface MechanicClient {

    @GetMapping("/api/mechanics/{assignmentId}/attribute/{attribute}")
    String getMechanicAttributeByAssignmentIdAndAttribute(
            @PathVariable("assignmentId") String assignmentId,
            @PathVariable("attribute") String attribute);
}
