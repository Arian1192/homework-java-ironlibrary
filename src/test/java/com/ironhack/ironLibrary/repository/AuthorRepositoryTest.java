package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author dummyAuthor;
    private Book dummyBook;

    @BeforeEach
    void setUp() {
        dummyBook = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
        dummyAuthor = new Author("Gene Kim", "g.kim@gmail.com", dummyBook);
        authorRepository.save(dummyAuthor);
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void shouldReturnACorrectBookIfUseFindBookByAuthor() {
        Optional<Book> maybeBook =  authorRepository.findBookByAuthor("Gene Kim");
        assertTrue(maybeBook.isPresent(), "The book should not be null");
        maybeBook.ifPresent(book -> {
            assertEquals("The unicorn project", book.getTitle());
            assertEquals("978-84-415-4301-0", book.getIsbn());
            assertEquals(1, book.getQuantity());
            assertEquals("novel", book.getCategory());
        });
    }

    @Test
    void shouldReturnEmptyIfWeWantToFindABookByAuthorThatNotExist(){
        Optional<Book> maybeBook = authorRepository.findBookByAuthor("J.K Rowling");
        assertFalse(maybeBook.isPresent());
    }
}