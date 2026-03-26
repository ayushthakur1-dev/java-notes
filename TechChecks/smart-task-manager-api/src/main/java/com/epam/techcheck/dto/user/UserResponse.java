package com.epam.techcheck.dto.user;

import com.epam.techcheck.enumeration.UserRole;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        UserRole role
) { }
