package com.epam.techcheck.service;

import com.epam.techcheck.dto.task.TaskCreateRequest;
import com.epam.techcheck.dto.task.TaskFilterRequest;
import com.epam.techcheck.entity.Task;
import com.epam.techcheck.entity.User;
import com.epam.techcheck.enumeration.TaskStatus;
import com.epam.techcheck.enumeration.UserRole;
import com.epam.techcheck.exception.InvalidTaskStateException;
import com.epam.techcheck.exception.InvalidUserRequestException;
import com.epam.techcheck.exception.ResourceNotFoundException;
import com.epam.techcheck.repository.TaskRepository;
import com.epam.techcheck.specification.TaskSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TaskService {

    private static final String TASK_NOT_FOUND_MESSAGE = "task not found with id: ";

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public Task getTaskById(UUID taskId) {
        return taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(TASK_NOT_FOUND_MESSAGE + taskId));
    }

    @Transactional
    public Task createTask(TaskCreateRequest request) {
        Task task = new Task(
                request.title(),
                request.description()
        );

        return taskRepository.save(task);
    }

    @Transactional
    public Task assignTask(UUID taskId, UUID userId) {
        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(TASK_NOT_FOUND_MESSAGE + taskId));

        if(task.getStatus() == TaskStatus.COMPLETED)
            throw new InvalidTaskStateException("completed tasks cannot be reassigned");

        User user = userService.getUserById(userId);

        if(user.isDeleted())
            throw new InvalidUserRequestException("cannot assign task to deleted user");

        task.setAssignedTo(user);
        task.setStatus(TaskStatus.ASSIGNED);
        return task;
    }

    @Transactional
    public void deleteTask(UUID taskId) {
        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(TASK_NOT_FOUND_MESSAGE + taskId));

        task.setDeleted(true);
    }

    @Transactional
    public Task markCompleted(UUID taskId, UUID userId) {
        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(TASK_NOT_FOUND_MESSAGE + taskId));

        if(task.getStatus() != TaskStatus.ASSIGNED || !task.getAssignedTo().getId().equals(userId))
            throw new InvalidUserRequestException("only assigned user can mark the task as completed");

        task.setStatus(TaskStatus.COMPLETED);

        return task;
    }

    public Page<Task> getTasks(TaskFilterRequest request, Pageable pageable, UserRole currentUserRole, UUID currentUserId) {
        Specification<Task> spec = Specification.where(null);

        if(currentUserRole != UserRole.ADMIN)
            spec = spec.and(TaskSpecification.assignedUserIdEquals(currentUserId));

        if(request.title() != null)
            spec = spec.and(TaskSpecification.titleLike(request.title()));

        if(request.status() != null)
            spec = spec.and(TaskSpecification.statusEquals(request.status()));

        if(request.assignedUserId() != null)
            spec = spec.and(TaskSpecification.assignedUserIdEquals(request.assignedUserId()));

        spec = spec.and(TaskSpecification.hasDeleted(
                        request.deleted() != null
                        && request.deleted() == true
                        && currentUserRole == UserRole.ADMIN)
        );

        return taskRepository.findAll(spec, pageable);
    }
}
