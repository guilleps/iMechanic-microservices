package com.imechanic.backend.principal_service.client;

import com.imechanic.backend.principal_service.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", configuration = FeignClientConfiguration.class)
public interface MechanicStepClient {

    @GetMapping("/api/mechanic-step/complete/{stepId}")
    boolean getCompleteStepByStepId(@PathVariable("stepId") Long stepId);
}
