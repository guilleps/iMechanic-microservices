package com.imechanic.backend.principal_service.repository;

import com.imechanic.backend.principal_service.controller.response.OperationDTOResponse;
import com.imechanic.backend.principal_service.enumeration.TypeService;
import com.imechanic.backend.principal_service.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT new com.imechanic.backend.principal_service.controller.response.OperationDTOResponse(o.id, o.name) FROM Operation o WHERE o.typeService = :typeService")
    Optional<List<OperationDTOResponse>> findAllOperationsByTypeService(@Param("typeService") TypeService typeService);

    @Query("SELECT CASE " +
            "WHEN :attribute = 'id' THEN cast(o.id as string) " +
            "WHEN :attribute = 'name' THEN o.name " +
            "WHEN :attribute = 'typeService' THEN o.typeService " +
            "WHEN :attribute = 'serviceStatus' THEN o.serviceStatus " +
            "ELSE null END " +
            "FROM Operation o " +
            "WHERE o.id = :id")
    Optional<String> findOperationAttributeByOperationIdAndAttribute(@Param("id") Long id, @Param("attribute") String attribute);
}
