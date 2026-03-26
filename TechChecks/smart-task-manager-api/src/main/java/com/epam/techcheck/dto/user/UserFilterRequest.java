package com.epam.techcheck.dto.user;

import com.epam.techcheck.enumeration.UserRole;

public record UserFilterRequest(
        String name,
        String email,
        UserRole role,
        Boolean deleted
) {}
