package com.ironhack.ironLibrary.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorTest {

   @Test
    void createAuthor(){
       Author authorOne = new Author();
       Author authorTwo = new Author();

       assertNotNull(authorOne);
       assertNotNull(authorTwo);
   }
}
