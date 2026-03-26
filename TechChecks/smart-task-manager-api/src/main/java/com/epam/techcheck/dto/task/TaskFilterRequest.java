package com.epam.techcheck.dto.task;

import com.epam.techcheck.enumeration.TaskStatus;

import java.util.UUID;

public record TaskFilterRequest(
        String title,
        TaskStatus status,
        UUID assignedUserId,
        Boolean deleted
) { }
