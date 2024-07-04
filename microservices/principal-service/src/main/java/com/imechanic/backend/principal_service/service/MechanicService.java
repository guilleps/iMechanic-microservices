package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.config.util.JwtUtils;
import com.imechanic.backend.principal_service.controller.request.MechanicDTORequest;
import com.imechanic.backend.principal_service.controller.response.MechanicDTOResponse;
import com.imechanic.backend.principal_service.controller.response.MechanicDetailsDTOResponse;
import com.imechanic.backend.principal_service.controller.response.OperationDTOResponse;
import com.imechanic.backend.principal_service.enumeration.RoleEnum;
import com.imechanic.backend.principal_service.exception.ResourceNotFoundException;
import com.imechanic.backend.principal_service.model.Assignment;
import com.imechanic.backend.principal_service.model.Mechanic;
import com.imechanic.backend.principal_service.model.Operation;
import com.imechanic.backend.principal_service.model.UserEntity;
import com.imechanic.backend.principal_service.repository.MechanicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MechanicService {
    private final MechanicRepository mechanicRepository;
    private final RoleService roleService;
    private final UserService userService;
    private final OperationService operationService;
    private final AssignmentService assignmentService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Map<String, String> createMechanic(MechanicDTORequest mechanicDTORequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity user = userService.findUserByEmail(email);

        List<Operation> operationsSelected = mechanicDTORequest.getOperationsIds().stream()
                .map(operationService::findById)
                .collect(Collectors.toList());

        Mechanic mechanic = Mechanic.builder()
                .email(mechanicDTORequest.getEmail())
                .name(mechanicDTORequest.getName())
                .password(passwordEncoder.encode(mechanicDTORequest.getPassword()))
                .phone(mechanicDTORequest.getPhone())
                .role(roleService.findByRoleEnum(RoleEnum.MECHANIC))
                .userEntity(user)
                .build();

        mechanicRepository.save(mechanic);

        for (Operation operation : operationsSelected) {
            Assignment assignment = Assignment.builder()
                    .mechanic(mechanic)
                    .operation(operation)
                    .build();
            assignmentService.saveAssignment(assignment);
        }

        Authentication authenticationMechanic = new UsernamePasswordAuthenticationToken(
                mechanic.getEmail(), mechanic.getPassword(),
                AuthorityUtils.createAuthorityList(mechanic.getRole().getRoleEnum().name())
        );

        String accessToken = jwtUtils.createToken(authenticationMechanic);
        String message = "Mechanic '" + mechanicDTORequest.getName() + "' successfully created.";

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("token", accessToken);

        log.info("Mechanic created: {}", mechanic.getEmail());
        return response;
    }

    @Transactional(readOnly = true)
    public List<MechanicDetailsDTOResponse> findMechanicsDetailsByWorkshop() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<Mechanic> mechanics = mechanicRepository.findMechanicsByUserEntityEmail(email);

        return mechanics.stream()
                .map(mechanic -> {
                    List<OperationDTOResponse> operations = mechanic.getAssignments().stream()
                            .map(assignment -> new OperationDTOResponse(assignment.getOperation().getId(), assignment.getOperation().getName()))
                            .collect(Collectors.toList());
                    return new MechanicDetailsDTOResponse(mechanic.getName(), mechanic.getPhone(), mechanic.getEmail(), operations);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MechanicDTOResponse> findAllMechanicsByWorkshop() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<Mechanic> mechanics = mechanicRepository.findMechanicsByUserEntityEmail(email);

        return mechanics.stream()
                .map(mechanic -> new MechanicDTOResponse(mechanic.getId(), mechanic.getName()))
                .collect(Collectors.toList());
    }

    public String getMechanicAttributeByAssignmentIdAndAttribute(String assignmentId, String attribute) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        return mechanicRepository.findMechanicAttributeByMechanicIdAndAttribute(assignment.getMechanic().getId(), attribute)
                .orElseThrow(() -> new ResourceNotFoundException("Attribute not found"));
    }
}
