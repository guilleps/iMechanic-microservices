package com.imechanic.backend.principal_service.controller;

import com.imechanic.backend.principal_service.controller.response.OperationDTOResponse;
import com.imechanic.backend.principal_service.enumeration.TypeService;
import com.imechanic.backend.principal_service.model.Operation;
import com.imechanic.backend.principal_service.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @GetMapping("/mantenimiento")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<List<OperationDTOResponse>> getOperationsTypeMaintenance() {
        List<OperationDTOResponse> response = operationService.findAllOperationsByType(TypeService.MANTENIMIENTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reparacion")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<List<OperationDTOResponse>> getOperationsTypeRepair() {
        List<OperationDTOResponse> response = operationService.findAllOperationsByType(TypeService.REPARACION);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<List<OperationDTOResponse>> getOperationsByWorkshop() {
        List<OperationDTOResponse> response = operationService.getOperationsByWorkshop();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/add")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<List<Operation>> addServicesToWorkshop(@RequestBody List<Long> operationsIds) {
        return ResponseEntity.ok(operationService.addServicesToWorkshop(operationsIds));
    }

    @GetMapping("/{assignmentId}/attribute/{attribute}")
    public ResponseEntity<String> getOperationAttributeByAssignmentIdAndAttribute(@PathVariable("assignmentId") String assignmentId, @PathVariable("attribute") String attribute) {
        return ResponseEntity.ok(operationService.getAttributeOperationByOperationIdAndAttribute(assignmentId, attribute));
    }

    @PostMapping("/{assignmentId}/operation/in/process")
    public ResponseEntity<Void> setOperationStatusToInProcessByAssignmentId(@PathVariable("assignmentId") String assignmentId) {
        operationService.setOperationStatusToInProcessByAssignmentId(assignmentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{assignmentId}/operation/to/finalized")
    public ResponseEntity<Void> setOperationStatusToFinalizedByAssignmentId(@PathVariable("assignmentId") String assignmentId) {
        operationService.setOperationStatusToFinalizedByAssignmentId(assignmentId);
        return ResponseEntity.ok().build();
    }
}
