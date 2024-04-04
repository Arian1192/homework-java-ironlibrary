package com.ironhack.ironLibrary.repository;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, String> {
    @Query("SELECT b from Issue i JOIN i.issueStudent c JOIN i.issueBook b WHERE c.usn = ?1")
    Optional<List<Book>> findAllBooksByUsn(String Usn);

}
