package com.ironhack.ironLibrary.utils;

public class BookWithActiveIssueException extends RuntimeException {
    public BookWithActiveIssueException(String s) {
        super(s);
    }
}
