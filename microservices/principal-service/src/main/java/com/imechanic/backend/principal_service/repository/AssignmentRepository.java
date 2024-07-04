package com.imechanic.backend.principal_service.repository;

import com.imechanic.backend.principal_service.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findAssignmentByOperationIdAndMechanicId(Long operationId, Long mechanicId);

    @Query("SELECT a.id FROM Assignment a WHERE a.mechanic.id = :mechanicId")
    List<Long> findAssignmentIdsByMechanicId(@Param("mechanicId") Long mechanicId);
}
