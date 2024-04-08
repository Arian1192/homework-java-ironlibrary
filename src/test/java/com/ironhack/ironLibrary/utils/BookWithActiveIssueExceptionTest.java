package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookWithActiveIssueExceptionTest {

    @Test
    public void testConstructor_setsMessage() {
        String expectedMessage = "Book has active issues";
        BookWithActiveIssueException exception = new BookWithActiveIssueException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testInheritance_isRuntimeException() {
        assertTrue(RuntimeException.class.isAssignableFrom(BookWithActiveIssueException.class));
    }
}
