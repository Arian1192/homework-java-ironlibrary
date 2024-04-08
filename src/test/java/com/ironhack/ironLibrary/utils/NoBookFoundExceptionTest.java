package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoBookFoundExceptionTest {
    @Test
    public void testConstructor_setsMessage() {
        String expectedMessage = "No book found";
        NoBookFoundException exception = new NoBookFoundException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testInheritance_isRuntimeException() {
        assertTrue(InstantiationException.class.isAssignableFrom(NoBookFoundException.class));
    }
}
