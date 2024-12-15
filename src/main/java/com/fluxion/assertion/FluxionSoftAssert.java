package com.fluxion.assertion;

import java.util.ArrayList;
import java.util.List;

public class FluxionSoftAssert {
    private List<String> errorMessages = new ArrayList<>();

    public void assertTrue(boolean condition, String message) {
        if (!condition) {
            errorMessages.add(message);
        }
    }

    public void assertFalse(boolean condition, String message) {
        if (condition) {
            errorMessages.add(message);
        }
    }

    public void assertEquals(Object actual, Object expected, String message) {
        if (!actual.equals(expected)) {
            errorMessages.add(message);
        }
    }

    // Fail method to manually add a failure message
    public void fail(String message) {
        errorMessages.add("FAIL: " + message);
    }

    public void assertAll() {
        if (!errorMessages.isEmpty()) {
            throw new AssertionError("Soft assertions failed: " + String.join(", ", errorMessages));
        }
    }
}
