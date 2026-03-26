package com.epam.techcheck.controller;

import com.epam.techcheck.dto.task.TaskCreateRequest;
import com.epam.techcheck.dto.task.TaskFilterRequest;
import com.epam.techcheck.dto.task.TaskResponse;
import com.epam.techcheck.entity.Task;
import com.epam.techcheck.enumeration.TaskStatus;
import com.epam.techcheck.enumeration.UserRole;
import com.epam.techcheck.security.CustomUserDetail;
import com.epam.techcheck.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID id) {
        Task task = taskService.getTaskById(id);

        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssignedTo()
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public Page<TaskResponse> getTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) UUID assignedUserId,
            @RequestParam(required = false) Boolean deleted,
            @PageableDefault(sort = "title") Pageable pageable,
            Authentication auth
    ) {
        System.out.println("1");
        UserRole role = UserRole.valueOf(
                auth.getAuthorities().iterator().next().getAuthority()
                        .replace("ROLE_", "")
        );

        System.out.println("2");
        CustomUserDetail userDetail = (CustomUserDetail) auth.getPrincipal();
        UUID userId = userDetail.getId();

        System.out.println("3");
        TaskFilterRequest request = new TaskFilterRequest(title, status, assignedUserId, deleted);

        System.out.println("4");
        Page<Task> tasks = taskService.getTasks(request, pageable ,role, userId);

        System.out.println("5");
        return tasks.map(
                task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getAssignedTo()
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskCreateRequest request) {
        Task task = taskService.createTask(request);

        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssignedTo()
        );

        return ResponseEntity
                .created(URI.create("/tasks/" + task.getId()))
                .body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{taskId}/assign")
    public ResponseEntity<TaskResponse> assignTask(
            @PathVariable UUID taskId,
            Authentication auth
    ) {
        CustomUserDetail userDetail = (CustomUserDetail) auth.getPrincipal();
        UUID userId = userDetail.getId();

        Task task = taskService.assignTask(taskId, userId);

        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssignedTo()
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<TaskResponse> markCompleted(
            @PathVariable UUID taskId,
            Authentication auth
    ) {
        CustomUserDetail userDetail = (CustomUserDetail) auth.getPrincipal();
        UUID userId = userDetail.getId();

        Task task = taskService.markCompleted(taskId, userId);

        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssignedTo()
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }
}
