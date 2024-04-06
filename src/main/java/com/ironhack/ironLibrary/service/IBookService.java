package com.ironhack.ironLibrary.service;
import com.ironhack.ironLibrary.model.Book;

import java.util.List;
import java.util.Optional;
public interface IBookService {
    Book save (Book book);
    Optional<Book> findByIsbn(String isbn);
    Optional<List<Book>> findAllByCategory(String Category);
    Optional<Book> findOneByTitle(String Title);

}
