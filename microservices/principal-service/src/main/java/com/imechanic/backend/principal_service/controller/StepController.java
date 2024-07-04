package com.imechanic.backend.principal_service.controller;

import com.imechanic.backend.principal_service.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/steps")
@RequiredArgsConstructor
public class StepController {
    private final StepService stepService;

    @GetMapping("/exists/{stepId}")
    public boolean existsStepById(@PathVariable("stepId") Long stepId) {
        return stepService.findById(stepId);
    }

    @GetMapping("/{stepId}/attribute/{attribute}")
    public String getStepAttributeByStepIdAndAttribute(@PathVariable("stepId") String stepId, @PathVariable("attribute") String attribute) {
        return stepService.getStepAttributeByStepIdAndAttribute(stepId, attribute);
    }
}
