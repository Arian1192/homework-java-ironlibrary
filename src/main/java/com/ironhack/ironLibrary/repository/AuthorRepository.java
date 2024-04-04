package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Query("SELECT b From Author a JOIN a.authorBook b WHERE a.name = ?1")
    Optional<Book> findBookByAuthor(String authorName);

    @Query("SELECT b,a FROM Author a JOIN Book b ON a.authorBook = b")
    Optional <List<Object[]>> findAllBooksWithAuthors();


}

// TO ASK
// DA IGUAL SI PASaMOS LOS DATOS EN LOWERCASE O UPPER ?
