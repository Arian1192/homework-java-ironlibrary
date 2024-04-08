package com.ironhack.ironLibrary.utils;

public class StudentExistsWithAnotherNameException extends RuntimeException{
    public StudentExistsWithAnotherNameException(String s) {
        super(s);
    }
}