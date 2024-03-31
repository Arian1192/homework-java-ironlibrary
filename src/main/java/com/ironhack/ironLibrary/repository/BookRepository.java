package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<List<Book>> findAllByCategory(String Category);
    Optional<Book> findOneByTitle(String Title);
}
