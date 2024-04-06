package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements IAuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Book> findBookByAuthor(String authorName) {
        return authorRepository.findBookByAuthor(authorName);
    }

    @Override
    public Optional<List<Object[]>> findAllBooksWithAuthors() {
        return authorRepository.findAllBooksWithAuthors();
    }

    @Override
    public Optional<Author> findByAuthorBook(Book book) {
        return authorRepository.findByAuthorBook(book);
    }
}
