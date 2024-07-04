package com.imechanic.backend.order_service.controller;

import com.imechanic.backend.order_service.service.MechanicStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mechanic-step")
@RequiredArgsConstructor
public class MechanicStepController {
    private final MechanicStepService mechanicStepService;

    @GetMapping("/complete/{stepId}")
    public boolean getCompleteStepByStepId(@PathVariable("stepId") Long stepId) {
        return mechanicStepService.getCompleteStepByStepId(stepId);
    }
}
