package com.ironhack.ironLibrary.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {
   static Book book;
   static Book book2;
   private Author dummyAuthor;
   private Author dummyAuthor2;
   @BeforeEach
   void setUp() {
      book = new Book("978-0-670-82162-0", "La isla misteriosa", "Sciense fiction", 12);
      book2 = new Book("978-0-670-82162-1", "Wuthering Heights", "Romance", 2);
      dummyAuthor = new Author("Julio Verne", "julio.verne@gmail.com", book);
      dummyAuthor2 = new Author("Emily Bronte", "emily.b@gmail.com",book2 );
   }

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

   @Test
   public void AuthorSetterNameTest() {
      dummyAuthor.setName("Verne Jules");
      assertEquals("Verne Jules", dummyAuthor.getName());
   }

   @Test
   public void AuthorGetterEmailTest() {
      assertEquals("emily.b@gmail.com", dummyAuthor2.getEmail());
   }

   @Test
   public void shouldBeTheSameBook(){
      assertEquals(book, dummyAuthor.getAuthorBook());
      assertEquals(book2,dummyAuthor2.getAuthorBook());
   }
}
