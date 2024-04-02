package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Student;
import org.junit.jupiter.api.AfterAll;
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
        Student student1 = new Student(1, "Student1");
        Student student2 = new Student(2, "Student2");
        studentRepository.save(student1);
        studentRepository.save(student2);
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
      //  studentRepository.flush();
    }

    @Test
    void checkSaveItem() {
        assertEquals(2, studentRepository.count());
    }

   @Test
    void findByUsnAndNameTest() {
        Optional<Student> student = studentRepository.findByUsnAndName(2, "Student2");
        assertTrue(student.isPresent());
        assertEquals("Student2", student.get().getName());
    }
}
