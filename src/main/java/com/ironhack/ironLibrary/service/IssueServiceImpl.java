package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements  IIssueService{

    @Autowired
    private IssueRepository issueRepository;
    @Override
    public List<Object[]> findAllBooksAndIssuesByUsn(String usn){
        Optional<List<Object[]>> optionalObjectsList = issueRepository.findAllBooksAndIssuesByUsn(usn);
        if (optionalObjectsList.isPresent()){
            return optionalObjectsList.get();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid usn"));
        }
    }

}
