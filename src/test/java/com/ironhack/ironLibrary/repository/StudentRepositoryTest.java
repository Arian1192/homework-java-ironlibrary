package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        Student student = new Student(1, "Student1");
        student = studentRepository.save(student);
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
        studentRepository.flush();
    }

   @Test
    void findByUsnAndNameTest() {
        Optional<Student> student = studentRepository.findByUsnAndName(1, "Student1");
        assertTrue(student.isPresent());
        assertEquals("Student1", student.get().getName());
    }
}
