package com.ironhack.ironLibrary.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class IssueTest {

    @Test
    public void createIssue(){
        Student student = new Student("09003688800", "Carlos");
        Book book = new Book();
        Issue IssueOne = new Issue();
        Issue IssueTwo = new Issue(student, book);

        assertNotNull(IssueOne);
        assertNotNull(IssueTwo);
        assertNotNull(IssueTwo.getIssueDate());
        assertNotNull(IssueTwo.getReturnDate());
        assertNotNull(IssueTwo);
        assertEquals(student, IssueTwo.getIssueStudent());
        assertEquals(book, IssueTwo.getIssueBook());
    }
}