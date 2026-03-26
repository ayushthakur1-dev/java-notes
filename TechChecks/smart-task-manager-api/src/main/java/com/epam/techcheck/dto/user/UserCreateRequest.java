package com.epam.techcheck.dto.user;

import com.epam.techcheck.enumeration.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateRequest(
        @Pattern(regexp = "^[A-Za-z ]{2,50}$", message = "Name must contain only letters, or spaces, and " +
                "cannot exceed 50 characters")
        String name,
        @NotBlank @Email(message = "The provided email address is invalid")
        String email,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&^#()\\-_=+]).{8,16}$",
        message = "Password must be min 8 and max 16 length containing at least 1 uppercase, 1 lowercase, 1 special " +
                "character, and 1 digit")
        String password,
        UserRole role
) { }
