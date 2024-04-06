package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.repository.AuthorRepository;
import com.ironhack.ironLibrary.repository.BookRepository;
import com.ironhack.ironLibrary.utils.InvalidBookInformationException;
import com.ironhack.ironLibrary.utils.NoBookFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceImplTest {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void testAddBookWithValidInformation() {
        List<String> validInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );

        assertDoesNotThrow(() -> menuService.addBook(validInformation));

    }

    @Test
    public void testAddBookWithInvalidInformation() {
        List<String> invalidInformation = Arrays.asList(
                "XXX-YYY-JJJ",
                "1 Book",
                "Fiction",
                "Superm@n",
                "my mail",
                "hey"
        );

        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class,
                () -> menuService.addBook(invalidInformation));
        assertEquals("The provided information is invalid. Please check the format", exception.getMessage());
    }

    @Test
    public void testAddBookWithIncompleteInformation() {
        List<String> incompleteInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com"
        );

        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class,
                () -> menuService.addBook(incompleteInformation));
        assertEquals("The book and author information must be a six-element string list.", exception.getMessage());
    }
    @Test
    public void testAddBookWithExtraInformation() {
        List<String> incompleteInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10",
                "10"
        );

        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class,
                () -> menuService.addBook(incompleteInformation));
        assertEquals("The book and author information must be a six-element string list.", exception.getMessage());
    }

    @Test
    public void testAddBookSaveAndFindByIsbn() {
        List<String> validInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );

        assertDoesNotThrow(() -> menuService.addBook(validInformation));

        Book savedBook = bookService.findByIsbn("978-84-415-4302-0").orElse(null);
        assertNotNull(savedBook);
        assertEquals("978-84-415-4302-0", savedBook.getIsbn());
        assertEquals("The fenix project", savedBook.getTitle());
        assertEquals("novel", savedBook.getCategory());
        assertEquals(10, savedBook.getQuantity());


        Author savedAuthor = authorService.findByAuthorBook(savedBook).get();
        assertNotNull(savedAuthor);
        assertEquals("Gene Kim", savedAuthor.getName());
        assertEquals("g.kim@gmail.com", savedAuthor.getEmail());
    }

    @Test
    void testSearchBookByAuthor() {
        List<String> validInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        assertDoesNotThrow(() -> menuService.addBook(validInformation));
        try{
           Book book = menuService.searchBookByAuthor("Gene Kim");
            assertEquals("The fenix project", book.getTitle());
            assertEquals("978-84-415-4302-0", book.getIsbn());

        }catch (NoBookFoundException e){
            fail("An NoBookFoundException exception was thrown when it was not expected.");
        }
    }

    @Test
    void testGetBookByAuthorGoesWrong(){
        String authorName = "Paco Wall";
        NoBookFoundException exception = assertThrows(NoBookFoundException.class, () -> menuService.searchBookByAuthor(authorName));
        assertEquals("No book found for author: " + authorName, exception.getMessage());
    }

    @Test
    void testGetBookByAuthorGoesWrongWhenAuthorNameIsAnEmptyString(){
        String authorName = "";
        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class, () -> menuService.searchBookByAuthor(authorName));
        assertEquals("The provided information is invalid. Please check the format", exception.getMessage());
    }



    private static List<List<String>> getListOfBooksToSave() {
        List<String> book1 = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        List<String> book2 = Arrays.asList(
                "978-84-415-4302-1",
                "The unicorn project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        List<List<String>> listOfBooksToSave = new ArrayList<>();
        listOfBooksToSave.add(book1);
        listOfBooksToSave.add(book2);
        return listOfBooksToSave;
    }

    @Test
    void testSearchBookByCategory() {
        List<List<String>> listOfBooksToSave = getListOfBooksToSave();
        for(List<String> book: listOfBooksToSave){
            menuService.addBook(book);
        }
        try{
            List<Book> listOfBooks = menuService.searchBookByCategory("novel");
            assertEquals(2, listOfBooks.size());
            assertEquals("The fenix project", listOfBooks.get(0).getTitle());
        }catch(NoBookFoundException e){
            fail("An NoBookFoundException exception was thrown when it was not expected.");
        }
    }

    @Test
    void testSearchBookByCategoryNotFoundAnyBook(){
        String Category = "comedy";
        NoBookFoundException exception = assertThrows(NoBookFoundException.class, () -> menuService.searchBookByCategory(Category));
        assertEquals("No books found for this category: " + Category, exception.getMessage());
    }

    @Test
    void testSearchBookByCategoryGoesWrong(){
        String Category = "";
        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class, () -> menuService.searchBookByCategory(Category));
        assertEquals("The provided information is invalid. Please check the format", exception.getMessage());
    }

    @AfterEach
    void tearDown() {

        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }
}