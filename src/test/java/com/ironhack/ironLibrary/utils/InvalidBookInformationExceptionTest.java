package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidBookInformationExceptionTest {
    @Test
    public void testConstructor_setsMessage() {
        String expectedMessage = "Invalid book information";
        InvalidBookInformationException exception = new InvalidBookInformationException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testInheritance_isRuntimeException() {
        assertTrue(RuntimeException.class.isAssignableFrom(InvalidBookInformationException.class));
    }
}
