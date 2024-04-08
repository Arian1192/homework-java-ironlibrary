package com.ironhack.ironLibrary.service;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.utils.NoBookFoundException;

import java.util.List;
public interface IMenuService {

    void mainManu() throws Exception;

    List<String> getNewBookInformation();

    public List<String> getNewIssueInformation();

    void addBook (List<String> bookAndAuthorInformation);

    Book searchBookByAuthor(String authorName) throws NoBookFoundException;

    List<Book> searchBookByCategory(String Category) throws NoBookFoundException;

    void issueBookToStudent(List<String> issueInformation) throws NoBookFoundException;

    List<Object[]> searchBooksByUsn(String usn) throws Exception;

    List<Object[]>  searchBooksAlongAuthors() throws NoBookFoundException;

}
