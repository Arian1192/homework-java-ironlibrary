package com.ironhack.ironLibrary.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class IssueTest {

    private Student student;
    private Book book;
    private Issue dummyIssue;
    private Issue dummyIssue2 = new Issue();

    @BeforeEach
    void setUp() {
        student = new Student("Bill");
        book = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
        dummyIssue = new Issue(student,book);
    }
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

    @Test
    public void IssueSetterStudentTest() {
        dummyIssue2.setIssueStudent(student);
        assertEquals(student, dummyIssue2.getIssueStudent());
    }

    @Test
    public void IssueGetterBookTest() {
        assertEquals(book, dummyIssue.getIssueBook());
    }

    @Test
    public void IssueGetterIssueDateTest(){
        assertNotNull(dummyIssue.getIssueDate());

    }
    @Test
    public void shouldBeEqualsIssueDateTest(){
        assertEquals(LocalDateTime.now(), dummyIssue.getIssueDate());
    }

    @Test
    public void IssueGetterReturnDateTest(){
        assertNotNull(dummyIssue.getReturnDate());
    }

    @Test
    public void shouldBeEqualsReturnDateTest(){
        assertEquals(LocalDateTime.now().plusDays(7), dummyIssue.getReturnDate());
    }
}