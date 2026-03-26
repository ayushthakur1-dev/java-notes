package com.epam.techcheck.controller;

import com.epam.techcheck.dto.user.UserCreateRequest;
import com.epam.techcheck.dto.user.UserFilterRequest;
import com.epam.techcheck.dto.user.UserResponse;
import com.epam.techcheck.entity.User;
import com.epam.techcheck.enumeration.UserRole;
import com.epam.techcheck.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        User user = service.getUserById(id);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public Page<UserResponse> getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) UserRole role,
            @RequestParam(required = false) Boolean deleted,
            @PageableDefault(size = 20, sort = "name") Pageable pageable,
            Authentication auth
    ) {

        UserRole currentUserRole = UserRole.valueOf(
          auth.getAuthorities().iterator().next().getAuthority()
                  .replace("ROLE_", "")
        );

        UserFilterRequest request = new UserFilterRequest(name, email, role, deleted);

        Page<User> users =  service.getUsers(request, pageable, currentUserRole);

        return users.map(
                user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<UserResponse> registerAdmin(@Valid @RequestBody UserCreateRequest request) {
        User user = service.createAdmin(request);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .body(response);
    }


    @PostMapping()
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserCreateRequest request) {
        User user = service.register(request);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .body(response);
    }
}
