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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorServiceImplTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IAuthorService authorService;

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
        List<Author> authorList = authorRepository.findAll();

        Author author1 = authorList.get(0);
        Author author2 = authorList.get(1);

        assertNotEquals(author1, author2);
    }

    @Test
    void findBookByAuthorPositiveTest(){
        Optional<Book> optionalAuthorService = authorService.findBookByAuthor(dummyAuthor.getName());
        assertTrue(optionalAuthorService.isPresent());
        Book book = optionalAuthorService.get();
        assertEquals("The unicorn project", book.getTitle());
        assertEquals("novel", book.getCategory());
        assertEquals(dummyBook, book);
    }

    @Test
    void findBookByAuthorNegativeTest(){
        Optional<Book> optionalAuthorService = authorService.findBookByAuthor("Gene Kime");
        assertFalse(optionalAuthorService.isPresent());
    }

    @Test
    void findAllBooksWithAuthorsPositiveTest(){
        List<Object[]> listObject = new ArrayList<>();
        List<Author> listAuthor = new ArrayList<>();
        List<Book> listBook = new ArrayList<>();

        Optional<List<Object[]>> optionalAuthorService = authorService.findAllBooksWithAuthors();
        assertTrue(optionalAuthorService.isPresent());
        listObject = optionalAuthorService.get();

        for( Object[] a : listObject){
            listBook.add((Book) a[0]);
            listAuthor.add((Author) a[1]);
        }

        assertEquals("Gene Kim", listAuthor.get(0).getName());
        assertEquals("The unicorn project", listBook.get(0).getTitle());
    }

    @Test
    void findBookByAuthorBookPositiveTest(){
        Optional<Author> optionalAuthorService = authorService.findByAuthorBook(dummyBook);
        assertTrue(optionalAuthorService.isPresent());
        Author author = optionalAuthorService.get();
        assertEquals("g.kim@gmail.com", author.getEmail());
        assertEquals(dummyBook, author.getAuthorBook());
        assertEquals(dummyAuthor, author);
    }

    @Test
    void findBookByAuthorBookNegativeTest(){
        Book book = new Book("978-84-415-4200-1", "New Book", "Historical", 12);
        Optional<Author> optionalAuthorService = authorService.findByAuthorBook(book);
        assertFalse(optionalAuthorService.isPresent());
    }

}