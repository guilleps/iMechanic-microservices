package com.imechanic.backend.order_service.repository;

import com.imechanic.backend.order_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignmentIdIn(List<String> assignmentIds);
}
