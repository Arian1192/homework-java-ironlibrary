package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import com.ironhack.ironLibrary.model.Student;

import java.util.List;
import java.util.Optional;

public interface IIssueService {
    Issue save(Student student, Book book
    );

   Optional<List<Book>> findAllBooksIssuedByUsn(String usn);

}
