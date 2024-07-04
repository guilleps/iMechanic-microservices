package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.enumeration.RoleEnum;
import com.imechanic.backend.principal_service.model.RoleEntity;
import com.imechanic.backend.principal_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public RoleEntity findByRoleEnum(RoleEnum roleEnum) {
        return roleRepository.findByRoleEnum(roleEnum);
    }
}
