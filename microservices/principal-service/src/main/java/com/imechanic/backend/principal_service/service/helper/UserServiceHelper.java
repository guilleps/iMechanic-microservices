package com.imechanic.backend.principal_service.service.helper;

import com.imechanic.backend.principal_service.client.MechanicStepClient;
import com.imechanic.backend.principal_service.controller.response.MechanicDTOResponse;
import com.imechanic.backend.principal_service.controller.response.OperationDTOResponse;
import com.imechanic.backend.principal_service.controller.response.OperationDetailsDTOResponse;
import com.imechanic.backend.principal_service.controller.response.StepDTOResponse;
import com.imechanic.backend.principal_service.model.Assignment;
import com.imechanic.backend.principal_service.model.Mechanic;
import com.imechanic.backend.principal_service.model.Operation;
import com.imechanic.backend.principal_service.model.Step;
import com.imechanic.backend.principal_service.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceHelper {
    private final AssignmentService assignmentService;
    private final MechanicStepClient mechanicStepClient;

    public OperationDetailsDTOResponse getOperationDetailsByAssignmentId(String assignmentId) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        Operation operation = assignment.getOperation();
        Mechanic mechanic = assignment.getMechanic();
        List<Step> steps = operation.getSteps();
        List<StepDTOResponse> stepDTOResponses = steps.stream()
                .map(step -> new StepDTOResponse(
                        step.getId(),
                        step.getName(),
                        step.getOrderStep(),
                        mechanicStepClient.getCompleteStepByStepId(step.getId())))
                .collect(Collectors.toList());

        return new OperationDetailsDTOResponse(
                new OperationDTOResponse(operation.getId(), operation.getName()),
                new MechanicDTOResponse(mechanic.getId(), mechanic.getName()),
                operation.getServiceStatus().name(),
                stepDTOResponses
        );
    }
}
