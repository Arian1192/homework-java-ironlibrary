package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import com.ironhack.ironLibrary.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;

    private Book dummyBook;
    private Book dummyBook2;
    private Student dummyStudent;

    private Student dummyStudent2;

    private Issue dummyIssue;
    private Issue dummyIssue2;


    @BeforeEach
    void setUp() {

        dummyStudent = new Student("09003688800", "Pedro");
        dummyStudent2 = new Student("09003688801","Maria");
        studentRepository.save(dummyStudent);

        dummyBook = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
        dummyBook2 = new Book("978-84-415-4302-0", "The fenix project", "novel", 1);
        bookRepository.saveAll(List.of(dummyBook, dummyBook2));

        dummyIssue = new Issue("03/04/2024", "10/04/2024",dummyStudent, dummyBook);
//        dummyIssue2 = new Issue("10/04/2024", "20/04/2024",dummyStudent, dummyBook2);
//        issueRepository.saveAll(List.of(dummyIssue, dummyIssue2));
            issueRepository.save(dummyIssue);
    }

    @AfterEach
    void tearDown() {
        issueRepository.deleteAll();
        bookRepository.deleteAll();
        studentRepository.deleteAll();

    }

    @Test
    void shouldFindOneBookIdWeUseFindAllBooksByUsn() {
        Optional<List<Book>> maybeListOfBooksIssued = issueRepository.findAllBooksByUsn(dummyStudent.getUsn());
        if(maybeListOfBooksIssued.isPresent()){
            List<Book> listOfBooksIssued = maybeListOfBooksIssued.get();
            assertEquals(1, listOfBooksIssued.size());
        }
    }

    @Test
    void shouldNotFindAnyBookIdWeUseFindAllBooksByUsn() {
        Optional<List<Book>> maybeListOfBooksIssued = issueRepository.findAllBooksByUsn(dummyStudent2.getUsn());
        if(maybeListOfBooksIssued.isPresent()){
            List<Book> listOfBooksIssued = maybeListOfBooksIssued.get();
            assertEquals(0, listOfBooksIssued.size());
        }
    }
}