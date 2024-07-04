package com.imechanic.backend.order_service.repository;

import com.imechanic.backend.order_service.model.MechanicStep;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicStepRepository extends MongoRepository<MechanicStep, String> {
    boolean existsByTaskIdAndStepId(String taskId, String stepId);

    List<MechanicStep> findAllByTaskId(String taskId);

    boolean existsByStepIdAndCompleteIsTrue(String stepId);
}
