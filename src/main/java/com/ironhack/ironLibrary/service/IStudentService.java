package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Student;

import java.util.Optional;

public interface IStudentService {
    public Optional<Student> findStudentByUsn(String usn);
}
