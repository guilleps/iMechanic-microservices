package com.imechanic.backend.principal_service.controller;

import com.imechanic.backend.principal_service.controller.request.MechanicDTORequest;
import com.imechanic.backend.principal_service.controller.response.MechanicDTOResponse;
import com.imechanic.backend.principal_service.controller.response.MechanicDetailsDTOResponse;
import com.imechanic.backend.principal_service.service.MechanicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mechanics")
@RequiredArgsConstructor
public class MechanicController {
    private final MechanicService mechanicService;

    @PostMapping("/crear")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<Map<String, String>> createMechanic(@Valid @RequestBody MechanicDTORequest mechanicDTORequest) {
        Map<String, String> response = mechanicService.createMechanic(mechanicDTORequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<List<MechanicDetailsDTOResponse>> findMechanicsDetailsByWorkshop() {
        List<MechanicDetailsDTOResponse> response = mechanicService.findMechanicsDetailsByWorkshop();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/order")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<List<MechanicDTOResponse>> getAllMechanicsByWorkshop() {
        List<MechanicDTOResponse> response = mechanicService.findAllMechanicsByWorkshop();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{assignmentId}/attribute/{attribute}")
    public ResponseEntity<String> getMechanicAttributeByAssignmentIdAndAttribute(
            @PathVariable("assignmentId") String assignmentId,
            @PathVariable("attribute") String attribute) {
        return ResponseEntity.ok(mechanicService.getMechanicAttributeByAssignmentIdAndAttribute(assignmentId, attribute));
    }
}
