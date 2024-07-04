package com.imechanic.backend.order_service.client;

import com.imechanic.backend.order_service.config.FeignClientConfiguration;
import com.imechanic.backend.order_service.controller.response.OperationDetailsDTOResponse;
import com.imechanic.backend.order_service.controller.response.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "principal-service", configuration = FeignClientConfiguration.class)
public interface UserClient {

    @GetMapping("/api/users/email/{email}/attribute/{attribute}")
    String getAttributeUserByEmail(@PathVariable("email") String email, @PathVariable("attribute") String attribute);

    @GetMapping("/api/users/{email}")
    UserDTOResponse getUserByEmail(@PathVariable("email") String email);

    @GetMapping("/api/users/operation-details/{assignmentId}")
    OperationDetailsDTOResponse getOperationDetailsByAssignmentId(@PathVariable("assignmentId") String assignmentId);
}
