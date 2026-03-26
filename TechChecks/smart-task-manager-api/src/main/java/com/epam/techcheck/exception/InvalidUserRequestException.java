package com.epam.techcheck.exception;

public class InvalidUserRequestException extends RuntimeException {
    public InvalidUserRequestException(String message) {
        super(message);
    }
}
