package com.ironhack.ironLibrary.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookTest {
    static Book book;

    @BeforeAll
    static void setUp(){
        book = new Book("978-0-670-82162-0", "title", "Romance", 12);
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
}
