package com.imechanic.backend.order_service.client;

import com.imechanic.backend.order_service.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "principal-service", configuration = FeignClientConfiguration.class)
public interface StepClient  {

    @GetMapping("/api/steps/exists/{stepId}")
    boolean existStep(@PathVariable("stepId") Long stepId);

    @GetMapping("/api/steps/{stepId}/attribute/{attribute}")
    String getStepAttributeByStepIdAndAttribute(@PathVariable("stepId") String stepId, @PathVariable("attribute") String attribute);
}
