package com.imechanic.backend.order_service.repository;

import com.imechanic.backend.order_service.model.MechanicStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicStepRepository extends JpaRepository<MechanicStep, Long> {
    boolean existsByTaskIdAndStepId(Long task_id, String stepId);

    List<MechanicStep> findAllByTaskId(Long task_id);

    boolean existsByStepIdAndCompleteIsTrue(String stepId);
}
