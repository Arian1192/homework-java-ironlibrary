package com.ironhack.ironLibrary.service;

import java.util.List;
import java.util.Optional;

public interface IIssueService {
    public List<Object[]> findAllBooksAndIssuesByUsn(String usn);
}
