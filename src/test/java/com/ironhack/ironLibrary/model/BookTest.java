package com.ironhack.ironLibrary.model;

import com.ironhack.ironLibrary.repository.BookRepository;
import com.ironhack.ironLibrary.utils.Validator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookTest {
    static Book book;
    private Book dummyBook;
    private Book dummyBook2;

    @BeforeEach
    void setUp() {
        book = new Book("978-0-670-82162-0", "title", "Romance", 12);
        dummyBook = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
        dummyBook2 = new Book("978-84-415-4301-0", "The unicorn project", "novel", 1);
    }

    @Test
    public void BookSetterAndGetterTitleTest() {
        book.setTitle("newTitle");
        assertEquals("newTitle", "newTitle");
    }

    @Test
    public void BookSetterAndGetterCategoryTest() {
        book.setCategory("terror");
        assertEquals("terror", book.getCategory());
    }

    @Test
    public void BookSetterAndGetterQuantityTest() {
        book.setQuantity(3);
        assertEquals(3, book.getQuantity());
    }

    @Test
    void BookDefaultConstructorTest(){
        Book book = new Book();
        assertNotNull(book);
    }

    @Test
    void shouldBeCorrectInstantiated(){
    assertEquals("novel", dummyBook.getCategory());
    assertEquals("978-84-415-4301-0", dummyBook.getIsbn());
    assertEquals("The unicorn project", dummyBook.getTitle());
    assertEquals(1, dummyBook.getQuantity());
    }

    @Test
    void shouldBeTheSameObject(){
        assertEquals(dummyBook, dummyBook2);
    }

    @Test
    void shouldBeTheSameHashCode(){
        assertEquals(dummyBook.hashCode(), dummyBook2.hashCode());
    }
    @Test
    void shouldBeUpdateQuantityToTen(){
        dummyBook.setQuantity(10);
        assertEquals(10, dummyBook.getQuantity());
    }
    @Test
    void shouldUpdateCategory(){
        dummyBook.setCategory("comedy");
        assertEquals("comedy", dummyBook.getCategory());
    }

    @Test
    void shouldUpdateTitle(){
        dummyBook.setTitle("The fenix project");
        assertEquals("The fenix project", dummyBook.getTitle());
    }

    @Test
    void shouldBePassISBNValidator(){
        assertTrue(Validator.checkISBNFormat(dummyBook.getIsbn()));
    }

    @Test
    void shouldBePassNotNullTitleValidator(){
        assertTrue(Validator.notBlankValidatorBooks(dummyBook.getTitle()));
    }

}
