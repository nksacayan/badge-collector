package com.nsac.badgecollectorserver.exceptions;

public class UserNotFoundException extends RuntimeException {

    // Default constructor
    public UserNotFoundException() {
        super("User not found");
    }

    // Constructor with custom message
    public UserNotFoundException(String message) {
        super(message);
    }

    // Constructor with custom message and cause
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause only
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}