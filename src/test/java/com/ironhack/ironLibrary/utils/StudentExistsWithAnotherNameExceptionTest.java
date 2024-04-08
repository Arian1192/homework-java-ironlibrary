package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentExistsWithAnotherNameExceptionTest {
    @Test
    public void testConstructor_setsMessage() {
        String expectedMessage = "A student with this name already exists.";
        StudentExistsWithAnotherNameException exception = new StudentExistsWithAnotherNameException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testInheritance_isRuntimeException() {
        assertTrue(RuntimeException.class.isAssignableFrom(StudentExistsWithAnotherNameException.class));
    }
}
