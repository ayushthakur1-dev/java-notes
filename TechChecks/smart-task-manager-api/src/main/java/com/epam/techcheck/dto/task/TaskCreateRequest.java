package com.epam.techcheck.dto.task;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskCreateRequest(
        @Nonnull @NotBlank @Size(min = 2, max = 50, message = "title must be min 2 and max 50 length")
        String title,
        @Nonnull @NotBlank @Size(min = 10, max = 150, message = "description must be min 10 and max 150 length ")
        String description
) {
}
