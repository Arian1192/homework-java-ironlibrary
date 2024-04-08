package com.ironhack.ironLibrary.service;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Optional<List<Book>> findAllByCategory(String category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public Optional<Book> findOneByTitle(String title) {
        return bookRepository.findOneByTitle(title);
    }

    @Override
    public Optional<List<Book>> findAllByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }
}
