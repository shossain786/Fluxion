package com.fluxion.exceptions;

public class LocatorNotFoundException extends RuntimeException {

    // Constructor with custom message
    public LocatorNotFoundException(String message) {
        super(message);
    }

    // Constructor with custom message and cause
    public LocatorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with only cause
    public LocatorNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "LocatorNotFoundException: " + getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}

