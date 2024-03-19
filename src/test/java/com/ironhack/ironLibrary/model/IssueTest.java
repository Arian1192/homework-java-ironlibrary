package com.ironhack.ironLibrary.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class IssueTest {

    @Test
    public void createIssue(){
        Student student = new Student(1, "Carlos");
        Book book = new Book();
        Issue IssueOne = new Issue();
        Issue IssueTwo = new Issue("17/03/2024", "24/03/2024", student, book);

        assertNotNull(IssueOne);
        assertNotNull(IssueTwo);
        assertEquals("17/03/2024", IssueTwo.getIssueDate());
        assertEquals("24/03/2024", IssueTwo.getReturnDate());
        assertEquals(student, IssueTwo.getIssueStudent());
        assertEquals(book, IssueTwo.getIssueBook());
    }

}