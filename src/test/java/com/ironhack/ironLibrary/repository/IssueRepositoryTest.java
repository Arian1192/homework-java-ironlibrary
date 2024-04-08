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
        Issue issue = new Issue();
        dummyStudent = new Student("09003688800", "Pedro");
        dummyStudent2 = new Student("09003688801", "Maria");
        studentRepository.save(dummyStudent);

        dummyBook = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
        dummyBook2 = new Book("978-84-415-4302-0", "The fenix project", "novel", 1);
        bookRepository.saveAll(List.of(dummyBook, dummyBook2));
        
        dummyIssue = new Issue(dummyStudent, dummyBook);
        issueRepository.save(issue);
    }

    @AfterEach
    void tearDown() {
        issueRepository.deleteAll();
        bookRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Test
    void shouldFindOneBookIdWeUseFindAllBooksByUsn() {
        issueRepository.save(dummyIssue);
        Optional<List<Book>> maybeListOfBooksIssued = issueRepository.findAllBooksByUsn(dummyStudent.getUsn());
        if (maybeListOfBooksIssued.isPresent()) {
            List<Book> listOfBooksIssued = maybeListOfBooksIssued.get();
            assertEquals(1, listOfBooksIssued.size());
        }
    }

    @Test
    void shouldNotFindAnyBookIdWeUseFindAllBooksByUsn() {
        Optional<List<Book>> maybeListOfBooksIssued = issueRepository.findAllBooksByUsn(dummyStudent2.getUsn());
        if (maybeListOfBooksIssued.isPresent()) {
            List<Book> listOfBooksIssued = maybeListOfBooksIssued.get();
            assertEquals(0, listOfBooksIssued.size());
        }
    }

    @Test
    void shouldFindAllBooksAndIssuesByUsn() {
        issueRepository.save(dummyIssue);
        Optional<List<Object[]>> optionalListObject = issueRepository.findAllBooksAndIssuesByUsn(dummyStudent.getUsn());
        if (optionalListObject.isPresent()) {
            List<Object[]> listObject = optionalListObject.get();
            Object[] firstElement = listObject.get(0);
            Book book = (Book) firstElement[0];
            Issue issue = (Issue) firstElement[1];
            assertEquals(1, listObject.size());
            assertEquals("The unicorn project", book.getTitle());
            assertEquals("Pedro", issue.getIssueStudent().getName());
        }
    }

    @Test
    void shouldNotFindBooksAndIssuesByUsn() {
        Optional<List<Object[]>> optionalListObject = issueRepository.findAllBooksAndIssuesByUsn(dummyStudent.getUsn());
        if (optionalListObject.isPresent()) {
            List<Object[]> listObject = optionalListObject.get();
            assertEquals(0, listObject.size());
        }
    }

    @Test
    void saveItemTest() {
        assertEquals(1, issueRepository.count());
    }

    @Test
    void deleteItemTest() {
        Optional<Issue> firstIssue = issueRepository.findAll().stream().findFirst();
        assertTrue(firstIssue.isPresent());
        issueRepository.delete(firstIssue.get());
        assertEquals(0, issueRepository.count());
    }
}

