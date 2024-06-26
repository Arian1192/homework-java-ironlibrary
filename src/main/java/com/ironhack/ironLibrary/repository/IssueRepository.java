package com.ironhack.ironLibrary.repository;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, String> {
    @Query("SELECT b FROM Issue i JOIN i.issueStudent c JOIN i.issueBook b WHERE c.usn = ?1")
    Optional<List<Book>> findAllBooksByUsn(String usn);

    @Query("SELECT b, i FROM Issue i JOIN i.issueStudent c JOIN i.issueBook b WHERE c.usn = ?1")
    Optional<List<Object[]>> findAllBooksAndIssuesByUsn(String usn);

    @Query("SELECT i FROM Issue i WHERE i.issueBook.isbn = ?1")
    Optional<Issue> findIssueByIsbn(String isbn);
}
