package com.ironhack.ironLibrary.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorTest {

   @Test
    void createAuthor(){
       Author authorOne = new Author();
       Book book = new Book("978-3-16-148410-0", "Test book", "Tester", 2);
       Author authorTwo = new Author("Carlos Diaz","carlosdiaz@mail.com", book);

       assertNotNull(authorOne);
       assertNotNull(authorTwo);
       assertEquals("Carlos Diaz", authorTwo.getName());
       assertEquals("Test book", authorTwo.getAuthorBook().getTitle());
   }
}
