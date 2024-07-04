package com.imechanic.backend.principal_service.controller;

import com.imechanic.backend.principal_service.controller.response.AssignmentDTOResponse;
import com.imechanic.backend.principal_service.controller.response.OperationDetailsDTOResponse;
import com.imechanic.backend.principal_service.controller.response.StepDTOResponse;
import com.imechanic.backend.principal_service.model.Mechanic;
import com.imechanic.backend.principal_service.model.Operation;
import com.imechanic.backend.principal_service.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping("/operationId/{operationId}/mechanicId/{mechanicId}")
    public ResponseEntity<Long> getAssignmentIdByOperationIdAndMechanicId(@PathVariable("operationId") Long operationId,
                                                                          @PathVariable("mechanicId") Long mechanicId) {
        return ResponseEntity.ok(assignmentService.getAssignmentIdByOperationIdAndMechanicId(operationId, mechanicId));
    }

    @GetMapping("/todas")
    public ResponseEntity<List<AssignmentDTOResponse>> getAllWorkshopAssignments() {
        List<AssignmentDTOResponse> allWorkshopAssignments = assignmentService.getAllWorkshopAssignments();
        return ResponseEntity.ok(allWorkshopAssignments);
    }

    @GetMapping("/mechanic/{email}/ids")
    public ResponseEntity<List<String>> getAssignmentIdsByMechanic(@PathVariable("email") String email) {
        List<String> assignmentIds = assignmentService.getAssignmentIdsByMechanicId(email);
        if (assignmentIds == null || assignmentIds.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assignmentIds);
    }

    @GetMapping("/{assignmentId}/operation/details")
    public ResponseEntity<OperationDetailsDTOResponse> getOperationDetailByAssignmentId(@PathVariable("assignmentId") String assignmentId) {
        return ResponseEntity.ok(assignmentService.findOperationDetailByAssignmentId(assignmentId));
    }

    @GetMapping("/{assignmentId}/operation/steps")
    public ResponseEntity<List<StepDTOResponse>> getOperationStepsByAssignmentId(@PathVariable("assignmentId") String assignmentId) {
        return ResponseEntity.ok(assignmentService.findOperationStepsByAssignmentId(assignmentId));
    }

    @GetMapping("/{assignmentId}/operation")
    public ResponseEntity<Operation> getOperationByAssignmentId(@PathVariable("assignmentId") String assignmentId) {
        return ResponseEntity.ok(assignmentService.findOperationByAssignmentId(assignmentId));
    }

    @GetMapping("/{assignmentId}/mechanic")
    public ResponseEntity<Mechanic> getMechanicByAssignmentId(@PathVariable("assignmentId") String assignmentId) {
        return ResponseEntity.ok(assignmentService.findMechanicByAssignmentId(assignmentId));
    }
}
