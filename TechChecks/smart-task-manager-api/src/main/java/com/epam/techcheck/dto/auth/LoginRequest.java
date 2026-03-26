package com.epam.techcheck.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

public record LoginRequest(
        @NonNull @NotBlank @Email
        String email,
        @NonNull @NotBlank
        String password
) { }
