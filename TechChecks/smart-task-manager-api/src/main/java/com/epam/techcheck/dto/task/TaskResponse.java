package com.epam.techcheck.dto.task;

import com.epam.techcheck.entity.User;
import com.epam.techcheck.enumeration.TaskStatus;

import java.util.UUID;

public record TaskResponse(
    UUID id,
    String title,
    String description,
    TaskStatus status,
    User assignedTo
) {}
