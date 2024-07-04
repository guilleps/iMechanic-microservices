package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.client.MechanicStepClient;
import com.imechanic.backend.principal_service.controller.response.*;
import com.imechanic.backend.principal_service.model.*;
import com.imechanic.backend.principal_service.repository.AssignmentRepository;
import com.imechanic.backend.principal_service.service.helper.MechanicServiceHelper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final UserService userService;
    private final MechanicServiceHelper mechanicServiceHelper;
    private final MechanicStepClient mechanicStepClient;

    public void saveAssignment(Assignment assignment) {
        try {
            assignmentRepository.save(assignment);
            log.info("Assignment saved successfully: {}", assignment);
        } catch (Exception e) {
            log.error("Error saving assignment: {}", e.getMessage());
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Transactional(readOnly = true)
    public Long getAssignmentIdByOperationIdAndMechanicId(Long operationId, Long mechanicId) {
        Assignment assignment = assignmentRepository.findAssignmentByOperationIdAndMechanicId(operationId, mechanicId)
                .orElseThrow(() -> new EntityNotFoundException("Assignment with operationId: " + operationId + " and mechanicId: " + mechanicId + " not found"));
        return assignment.getId();
    }

    @Transactional(readOnly = true)
    public List<AssignmentDTOResponse> getAllWorkshopAssignments() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity user = userService.findUserByEmail(email);

        List<Mechanic> mechanics = user.getMechanics();

        List<AssignmentDTOResponse> assignmentDTOResponses = new ArrayList<>();
        for (Mechanic mechanic : mechanics) {
            List<Assignment> assignments = mechanic.getAssignments();
            for (Assignment assignment : assignments) {
                Operation operation = assignment.getOperation();
                assignmentDTOResponses.add(new AssignmentDTOResponse(operation.getId(), mechanic.getId()));
            }
        }

        return assignmentDTOResponses;
    }

    @Transactional(readOnly = true)
    public List<String> getAssignmentIdsByMechanicId(String email) {
        Mechanic mechanic = mechanicServiceHelper.findMechanicByEmail(email);

        List<Long> assignmentIds = assignmentRepository.findAssignmentIdsByMechanicId(mechanic.getId());

        return assignmentIds.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public OperationDetailsDTOResponse findOperationDetailByAssignmentId(String assignmentId) {
        Assignment assignment = getAssignmentById(assignmentId);

        Operation operation = assignment.getOperation();
        Mechanic mechanic = assignment.getMechanic();
        List<Step> steps = operation.getSteps();
        List<StepDTOResponse> stepDTOs = steps.stream()
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
                stepDTOs
        );
    }

    public Assignment getAssignmentById(String assignmentId) {
        return assignmentRepository.findById(Long.valueOf(assignmentId))
                .orElseThrow(() -> new EntityNotFoundException("Assignment with ID: " + assignmentId + " not found"));
    }

    public List<StepDTOResponse> findOperationStepsByAssignmentId(String assignmentId) {
        Assignment assignment = getAssignmentById(assignmentId);
        Operation operation = assignment.getOperation();

        return operation.getSteps().stream()
                .map((step -> new StepDTOResponse(
                        step.getId(),
                        step.getName(),
                        step.getOrderStep(),
                        mechanicStepClient.getCompleteStepByStepId(step.getId()))))
                .toList();
    }

    public Operation findOperationByAssignmentId(String assignmentId) {
        Assignment assignment = getAssignmentById(assignmentId);
        return assignment.getOperation();
    }

    public Mechanic findMechanicByAssignmentId(String assignmentId) {
        Assignment assignment = getAssignmentById(assignmentId);
        return assignment.getMechanic();
    }
}
