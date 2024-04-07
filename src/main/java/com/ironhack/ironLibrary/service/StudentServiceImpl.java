package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Student;
import com.ironhack.ironLibrary.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService{


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> findStudentByUsnAndName(String usn, String name) {
        return studentRepository.findByUsnAndName(usn, name);
    }
    @Override
    public Student save(String usn, String name) {
        return studentRepository.save(new Student(usn, name));
    }
    @Override
    public Optional<Student> findStudentByUsn(String usn) {
        return studentRepository.findByUsn(usn);

    }
}
