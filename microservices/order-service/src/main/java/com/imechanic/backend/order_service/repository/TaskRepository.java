package com.imechanic.backend.order_service.repository;

import com.imechanic.backend.order_service.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, Long> {
    List<Task> findByAssignmentIdIn(List<String> assignmentIds);
}
