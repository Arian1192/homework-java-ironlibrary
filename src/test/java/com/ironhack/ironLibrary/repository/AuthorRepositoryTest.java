package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
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
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    private List<Author> dummyListAuthor;
    private List<Book> dummyListBook;

    @BeforeEach
    void setUp() {
        dummyListBook = new ArrayList<>();
        dummyListBook.add(new Book("978-84-415-4301-0", "The unicorn project", "novel", 1));
        dummyListBook.add(new Book("978-84-415-4301-1", "Siddarta", "novel",3));
        dummyListBook.add(new Book("978-84-415-4301-2","La condesa sangrienta", "novel", 10));
        dummyListBook.add(new Book("978-84-415-4301-3","Sherlock Holmes","crime",1 ));

        dummyListAuthor = new ArrayList<>();
        dummyListAuthor.add(new Author("Gene Kim", "g.kim@gmail.com", dummyListBook.get(0)));
        dummyListAuthor.add(new Author("Herman Hesse", "h.hesse@gmail.com",dummyListBook.get(1)));
        dummyListAuthor.add(new Author("Alejandra Pizarnik", "alejandra.pizarnik@gmail.com",dummyListBook.get(2)));
        dummyListAuthor.add(new Author("Arthur Conan Doyle","a.c.doyle@mail.com", dummyListBook.get(3)));

        authorRepository.saveAll(dummyListAuthor);
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

    @Test
    void getAllBooksWithAuthor(){
        List<Object[]> listObject = new ArrayList<>();

        Optional<List<Object[]>> getBooks = authorRepository.findAllBooksWithAuthors();
        if(getBooks.isPresent()){
            listObject = getBooks.get();
        }
        assertEquals(4, listObject.size());
    }

    @Test
    void allBookAuthorsAreCorrect(){
        List<Object[]> listObject = new ArrayList<>();
        List<Author> listAuthor = new ArrayList<>();

        Optional<List<Object[]>> getBooks = authorRepository.findAllBooksWithAuthors();

        if(getBooks.isPresent()){
            listObject = getBooks.get();
        }
        for( Object[] a : listObject){
            listAuthor.add((Author) a[1]);
        }

        assertEquals("Gene Kim", listAuthor.get(0).getName());
        assertEquals("Herman Hesse", listAuthor.get(1).getName());
        assertEquals("Alejandra Pizarnik", listAuthor.get(2).getName());
        assertEquals("Arthur Conan Doyle", listAuthor.get(3).getName());

    }


}