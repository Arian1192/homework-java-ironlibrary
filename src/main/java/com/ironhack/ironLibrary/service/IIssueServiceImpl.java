package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import com.ironhack.ironLibrary.model.Student;
import com.ironhack.ironLibrary.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IIssueServiceImpl implements IIssueService{

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Issue save(Student student, Book book) {
        return issueRepository.save(new Issue(student, book));
    }

    @Override
    public Optional<List<Book>> findAllBooksIssuedByUsn(String usn) {
        return issueRepository.findAllBooksByUsn(usn);
    }
}
