package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.controller.response.OperationDTOResponse;
import com.imechanic.backend.principal_service.enumeration.ServiceStatus;
import com.imechanic.backend.principal_service.enumeration.TypeService;
import com.imechanic.backend.principal_service.exception.ResourceNotFoundException;
import com.imechanic.backend.principal_service.model.Assignment;
import com.imechanic.backend.principal_service.model.Operation;
import com.imechanic.backend.principal_service.model.UserEntity;
import com.imechanic.backend.principal_service.repository.OperationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;
    private final UserService userService;
    private final AssignmentService assignmentService;

    @Transactional(readOnly = true)
    public Operation findById(Long operationId) {
        return operationRepository.findById(operationId)
                .orElseThrow(() -> new EntityNotFoundException("Operation with ID: " + operationId + " not found"));
    }

    @Transactional(readOnly = true)
    public List<OperationDTOResponse> findAllOperationsByType(TypeService typeService) {
        return operationRepository.findAllOperationsByTypeService(typeService)
                .orElseThrow(() -> new EntityNotFoundException("No services were found for the specified type."));
    }

    @Transactional(readOnly = true)
    public List<OperationDTOResponse> getOperationsByWorkshop() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity user = userService.findUserByEmail(email);

        List<Operation> operationsCurrently = user.getOperations();

        return operationsCurrently.stream()
                .map(operation -> new OperationDTOResponse(operation.getId(), operation.getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public String getAttributeOperationByOperationIdAndAttribute(String assignmentId, String attribute) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        return operationRepository.findOperationAttributeByOperationIdAndAttribute(assignment.getOperation().getId(), attribute)
                .orElseThrow(() -> new ResourceNotFoundException("Attribute not found"));
    }

    @Transactional
    public List<Operation> addServicesToWorkshop(List<Long> operationsIds) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity user = userService.findUserByEmail(email);

        List<Operation> operationsCurrently = user.getOperations();

        operationsCurrently.removeIf(operation -> !operationsIds.contains(operation.getId()));

        List<Operation> newsOperations = operationRepository.findAllById(operationsIds);
        for (Operation newOperation : newsOperations) {
            if (!operationsCurrently.contains(newOperation)) {
                operationsCurrently.add(newOperation);
            }
        }

        user.setOperations(operationsCurrently);
        userService.saveUser(user);

        return operationsCurrently;
    }

    public void setOperationStatusToInProcessByAssignmentId(String assignmentId) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        assignment.getOperation().setServiceStatus(ServiceStatus.EN_PROCESO);
        operationRepository.save(assignment.getOperation()); // Guardar cambios en la base de datos
    }

    public void setOperationStatusToFinalizedByAssignmentId(String assignmentId) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        assignment.getOperation().setServiceStatus(ServiceStatus.FINALIZADO);
        operationRepository.save(assignment.getOperation()); // Guardar cambios en la base de datos
    }
}
