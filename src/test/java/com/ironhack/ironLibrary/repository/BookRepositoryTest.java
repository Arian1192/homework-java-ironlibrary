package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    int quantityOfNovelBooks = 0;
    @Autowired
    private BookRepository bookRepository;

    private Book dummyBook;
    @BeforeEach
    void setUp() {
        dummyBook = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
        bookRepository.save(dummyBook);
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldBeGetTheBook(){
        Optional<Book> maybeBook = bookRepository.findById("978-84-415-4301-0");
        assertTrue(maybeBook.isPresent());
    }


    @Test
    void shouldBeOneQuantityBook(){
        Optional<Book> maybeBook = bookRepository.findById("978-84-415-4301-0");
        if(maybeBook.isPresent()){
            Book book = maybeBook.get();
            assertEquals(1, book.getQuantity());
        }
    }

    @Test
    void shouldBeTheCorrectTitle(){
        Optional<Book> maybeBook = bookRepository.findById("978-84-415-4301-0");
        if(maybeBook.isPresent()){
            Book book = maybeBook.get();
            assertEquals("The unicorn project", book.getTitle());
        }
    }

    @Test
    void shouldBeTheCorrectCategory(){
        Optional<Book> maybeBook = bookRepository.findById("978-84-415-4301-0");
        if(maybeBook.isPresent()){
            Book book = maybeBook.get();
            assertEquals("novel", book.getCategory());
        }
    }

    @Test
    void shouldGetAllBooksUsingFindAllByCategory() {
        Book newBook = new Book("978-84-415-4302-0", "The fenix project", "novel", 10);
        bookRepository.save(newBook);
        Optional<List<Book>> maybeListOfBooks = bookRepository.findAllByCategory("novel");
        if(maybeListOfBooks.isPresent()){
            List<Book> listOfBooks = maybeListOfBooks.get();
            assertEquals(2, listOfBooks.size());
        }
    }

    @Test
    void shouldGetTheRightQuantityOfBooksUsingFindAllByCategory(){
        Book newBooks = new Book("978-84-415-4302-0", "The fenix project", "novel", 10);
        bookRepository.save(newBooks);
        Optional<List<Book>> maybeListOfBooks = bookRepository.findAllByCategory("novel");
        if(maybeListOfBooks.isPresent()){
            maybeListOfBooks.get().forEach(book -> {
                quantityOfNovelBooks = quantityOfNovelBooks + book.getQuantity();
            });
            assertEquals(11, quantityOfNovelBooks);
        }
    }
}