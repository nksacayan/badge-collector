package com.nsac.badgecollectorserver.exceptions;

/**
 * Exception thrown when a badge is not found in the system.
 */
public class BadgeNotFoundException extends RuntimeException {

    /**
     * Constructs a new BadgeNotFoundException with no detail message.
     */
    public BadgeNotFoundException() {
        super("Badge not found.");
    }

    /**
     * Constructs a new BadgeNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public BadgeNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new BadgeNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public BadgeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new BadgeNotFoundException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public BadgeNotFoundException(Throwable cause) {
        super(cause);
    }
}