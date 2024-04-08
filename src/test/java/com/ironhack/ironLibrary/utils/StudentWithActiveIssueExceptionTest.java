package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentWithActiveIssueExceptionTest {
    @Test
    public void testConstructor_setsMessage() {
        String expectedMessage = "Student already has an active Issue.";
        StudentWithActiveIssueException exception = new StudentWithActiveIssueException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testInheritance_isRuntimeException() {
        assertTrue(RuntimeException.class.isAssignableFrom(StudentWithActiveIssueException.class));
    }
}
