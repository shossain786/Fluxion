package com.fluxion.exceptions;

public class FluxionException extends Exception {

    // Constructor to create an exception with a custom message
    public FluxionException(String message) {
        super(message);
    }

    // Constructor to create an exception with a custom message and cause
    public FluxionException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor to create an exception with only a cause
    public FluxionException(Throwable cause) {
        super(cause);
    }

    // Override the toString() method to provide custom exception details
    @Override
    public String toString() {
        return "FrameworkException: " + getMessage();
    }

    // You can also log the exception or add additional logic if needed
    @Override
    public void printStackTrace() {
        // Custom logic for logging or printing stack trace can be added here
        super.printStackTrace();
    }
}
