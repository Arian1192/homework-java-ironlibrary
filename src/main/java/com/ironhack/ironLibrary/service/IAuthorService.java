package com.ironhack.ironLibrary.service;
import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    Author save (Author author);
    Optional<Book> findBookByAuthor(String authorName);
    Optional <List<Object[]>> findAllBooksWithAuthors();

    Optional <Author> findByAuthorBook(Book book);
}
