package com.ironhack.ironLibrary.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {


    @Test
    void createStudent(){

        Student studentQA = new Student();
        Student studentQA2 = new Student(1,"Test");

        assertNotNull(studentQA);
        assertNotNull(studentQA2);

    }

}