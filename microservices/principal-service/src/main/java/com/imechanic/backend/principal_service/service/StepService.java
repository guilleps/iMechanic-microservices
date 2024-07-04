package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.exception.ResourceNotFoundException;
import com.imechanic.backend.principal_service.model.Step;
import com.imechanic.backend.principal_service.repository.StepRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StepService {
    private final StepRepository stepRepository;

    public boolean findById(Long stepId) {
        return stepRepository.existsById(stepId);
    }

    public String getStepAttributeByStepIdAndAttribute(String stepId, String attribute) {
        Step step = getStepById(stepId);
        return stepRepository.findStepAttributeByStepIdAndAttribute(step.getId(), attribute)
                .orElseThrow(() -> new ResourceNotFoundException("Attribute not found"));
    }

    private Step getStepById(String stepId) {
        return stepRepository.findById(Long.valueOf(stepId))
                .orElseThrow(() -> new EntityNotFoundException("Step with ID: " + stepId + " not found"));
    }
}
