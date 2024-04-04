package com.ironhack.ironLibrary.service;

import java.util.List;

public interface IMenuService {

    void addBook (List<String> bookAndAuthorInformation);

    List<String> getNewBookInformation();

}
