package com.epam.techcheck.service;

import com.epam.techcheck.dto.user.UserCreateRequest;
import com.epam.techcheck.dto.user.UserFilterRequest;
import com.epam.techcheck.entity.User;
import com.epam.techcheck.enumeration.UserRole;
import com.epam.techcheck.exception.EmailAlreadyExistsException;
import com.epam.techcheck.exception.ResourceNotFoundException;
import com.epam.techcheck.exception.UnauthorizedRoleAssignmentException;
import com.epam.techcheck.repository.UserRepository;
import com.epam.techcheck.specification.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with email: " + email));
    }

    public Page<User> getUsers(UserFilterRequest request, Pageable pageable, UserRole currentUserRole) {
        Specification<User> spec = Specification.where(null);

        if(request.name() != null)
            spec = spec.and(UserSpecification.nameContainsIgnoreCase(request.name()));

        if(request.email() != null)
            spec = spec.and(UserSpecification.emailEquals(request.email()));

        if(request.role() != null)
            spec = spec.and(UserSpecification.hasRole(request.role()));

        spec = spec.and(UserSpecification.hasDeleted(currentUserRole == UserRole.ADMIN && request.deleted() == true));

        return userRepository.findAll(spec, pageable);
    }

    @Transactional
    public User createAdmin(UserCreateRequest request) {
        if(request.role() != UserRole.ADMIN)
            throw new UnauthorizedRoleAssignmentException("regular users should be registered");

        String email = extractEmail(request.email());

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = new User(
                request.name().trim(),
                email,
                encodedPassword,
                UserRole.ADMIN
        );

        return userRepository.save(user);
    }

    @Transactional
    public User register(UserCreateRequest request) {
        if(request.role() == UserRole.ADMIN)
            throw new UnauthorizedRoleAssignmentException("admin can only be made by another admin");

        String email = extractEmail(request.email());

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = new User(
                request.name().trim(),
                email,
                encodedPassword,
                UserRole.REGULAR
        );

        return userRepository.save(user);
    }

    private String extractEmail(String email) {
        String validatedEmail = email.trim().toLowerCase();

        if(userRepository.existsByEmailAndDeletedFalse(validatedEmail))
            throw new EmailAlreadyExistsException("email already exists");

        return validatedEmail;
    }
}
