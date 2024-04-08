package com.ironhack.ironLibrary.utils;

public class StudentWithActiveIssueException extends RuntimeException {
    public StudentWithActiveIssueException(String s) {
        super(s);
    }
}