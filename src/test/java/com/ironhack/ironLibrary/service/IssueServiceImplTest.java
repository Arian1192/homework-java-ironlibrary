package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import com.ironhack.ironLibrary.model.Student;
import com.ironhack.ironLibrary.repository.BookRepository;
import com.ironhack.ironLibrary.repository.IssueRepository;
import com.ironhack.ironLibrary.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IssueServiceImplTest {
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private IIssueService issueService;

    private Book dummyBook;
    private Book dummyBook2;
    private Student dummyStudent;
    private Student dummyStudent2;
    private Issue dummyIssue;
    private Issue dummyIssue2;

    @BeforeEach
    void setUp() {
        dummyStudent = new Student("09003688800", "Pedro");
        studentRepository.save(dummyStudent);
        dummyBook = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
        bookRepository.save(dummyBook);
        Issue issue = new Issue(dummyStudent, dummyBook);
        issueRepository.save(issue);
    }

    @AfterEach
    void tearDown() {
        issueRepository.deleteAll();
        bookRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Test
    void findAllBooksIssuedByUsnPositiveTest(){
        Optional<List<Book>> optionalBookListService = issueService.findAllBooksIssuedByUsn(dummyStudent.getUsn());
        assertTrue(optionalBookListService.isPresent());
        List<Book> bookList1 = optionalBookListService.get();
        assertEquals(1, bookList1.size());
        assertEquals("The unicorn project", bookList1.get(0).getTitle());

    }
}
