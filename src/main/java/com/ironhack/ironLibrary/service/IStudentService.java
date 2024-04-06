package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IStudentService {
    Optional<Student> findStudentByUsnAndName(String usn, String name);
    Student save(String usn, String name);

}
