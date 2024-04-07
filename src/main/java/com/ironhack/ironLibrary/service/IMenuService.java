package com.ironhack.ironLibrary.service;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.utils.NoBookFoundException;

import java.util.List;
public interface IMenuService {
    void addBook (List<String> bookAndAuthorInformation);
    List<String> getNewBookInformation();

    Book searchBookByAuthor(String authorName) throws NoBookFoundException;

    List<Book> searchBookByCategory(String Category) throws NoBookFoundException;

    void issueBookToStudent(List<String> issueInformation) throws NoBookFoundException;
}
