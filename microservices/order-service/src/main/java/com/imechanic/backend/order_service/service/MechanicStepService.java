package com.imechanic.backend.order_service.service;

import com.imechanic.backend.order_service.model.MechanicStep;
import com.imechanic.backend.order_service.repository.MechanicStepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MechanicStepService {
    private final MechanicStepRepository mechanicStepRepository;

    public void saveMechanicStep(MechanicStep mechanicStep) {
        mechanicStepRepository.save(mechanicStep);
    }

    public boolean existsByTaskIdAndStepId(Long taskId, Long stepId) {
        return mechanicStepRepository.existsByTaskIdAndStepId(taskId, String.valueOf(stepId));
    }

    public List<MechanicStep> findAllByTask(String taskId) {
        return mechanicStepRepository.findAllByTaskId(Long.valueOf(taskId));
    }

    public boolean getCompleteStepByStepId(Long stepId) {
        return mechanicStepRepository.existsByStepIdAndCompleteIsTrue(stepId.toString());
    }
}
