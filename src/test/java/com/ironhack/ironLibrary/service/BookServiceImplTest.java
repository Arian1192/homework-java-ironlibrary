package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.repository.AuthorRepository;
import com.ironhack.ironLibrary.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IBookService bookService;

    private Author dummyAuthor, dummyAuthor2;
    private Book dummyBook, dummyBook2;

    @BeforeEach
    void setUp() {
        dummyBook = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
        dummyAuthor = new Author("Gene Kim", "g.kim@gmail.com", dummyBook);
        dummyBook2 = new Book("978-84-415-4301-1", "The unicorn project II", "novel", 1);
        dummyAuthor2 = new Author("Gene Kim2", "g.kim@gmail.com", dummyBook2);
        authorRepository.save(dummyAuthor);
        authorService.save(dummyAuthor2);
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void validateSameBehaviorUsingRepositoryOrServiceClass(){

        Book book1  = bookService.findByIsbn("978-84-415-4301-0").get();
        Book book2  = bookService.findByIsbn("978-84-415-4301-1").get();

        assertNotEquals(book1, book2);
    }


}