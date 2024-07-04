package com.imechanic.backend.principal_service.controller;

import com.imechanic.backend.principal_service.controller.response.InformationResponseDTO;
import com.imechanic.backend.principal_service.controller.response.OperationDetailsDTOResponse;
import com.imechanic.backend.principal_service.controller.response.UserDTOResponse;
import com.imechanic.backend.principal_service.service.UserService;
import com.imechanic.backend.principal_service.service.helper.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserServiceHelper userServiceHelper;

    @GetMapping("/email/{email}/attribute/{attribute}")
    public ResponseEntity<String> getEmailUserByEmail(@PathVariable("email") String email, @PathVariable("attribute") String attribute) {
        return ResponseEntity.ok(userService.getAttributeUserByEmail(email, attribute));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTOResponse> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserDTOByEmail(email));
    }

    @GetMapping("/operation-details/{assignmentId}")
    public ResponseEntity<OperationDetailsDTOResponse> getOperationDetailsByAssignmentId(@PathVariable("assignmentId") String assignmentId) {
        return ResponseEntity.ok(userServiceHelper.getOperationDetailsByAssignmentId(assignmentId));
    }

    @GetMapping("/info")
    public ResponseEntity<InformationResponseDTO> getInformationByUser() {
        return ResponseEntity.ok(userService.getInformationByUser());
    }
}
