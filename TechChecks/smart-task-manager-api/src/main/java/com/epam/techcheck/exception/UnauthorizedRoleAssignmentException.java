package com.epam.techcheck.exception;

public class UnauthorizedRoleAssignmentException extends RuntimeException{
    public UnauthorizedRoleAssignmentException(String message) {
        super(message);
    }
}
