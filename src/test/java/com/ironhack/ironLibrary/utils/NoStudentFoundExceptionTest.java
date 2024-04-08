package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoStudentFoundExceptionTest {
    @Test
    public void testConstructor_setsMessage() {
        String expectedMessage = "No book found";
        NoStudentFoundException exception = new NoStudentFoundException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testInheritance_isRuntimeException() {
        assertTrue(InstantiationException.class.isAssignableFrom(NoStudentFoundException.class));
    }
}
