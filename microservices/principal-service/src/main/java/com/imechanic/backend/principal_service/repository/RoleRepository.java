package com.imechanic.backend.principal_service.repository;

import com.imechanic.backend.principal_service.enumeration.RoleEnum;
import com.imechanic.backend.principal_service.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRoleEnum(RoleEnum roleEnum);
}
