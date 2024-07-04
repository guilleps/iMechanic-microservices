package com.imechanic.backend.order_service.service;

import com.imechanic.backend.order_service.model.Task;
import com.imechanic.backend.order_service.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional
    public void createTask(Task task) {
        try {
            taskRepository.save(task);
            log.info("Task saved successfully: {}", task);
        } catch (Exception e) {
            log.error("Error saving task: {}", e.getMessage());
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasksByAssignmentId(List<String> assignmentIds) {
        return taskRepository.findByAssignmentIdIn(assignmentIds);
    }
}
