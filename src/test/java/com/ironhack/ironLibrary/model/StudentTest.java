package com.ironhack.ironLibrary.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student dummyStudent;
    private Student dummyStudent2;
    @BeforeEach
    void setUp() {
        dummyStudent = new Student("09003688800","Fran");
        dummyStudent2 = new Student("09003688800","Fran");
    }
    @Test
    void createStudent(){
        Student studentQA = new Student();
        Student studentQA2 = new Student("09003688800","Test");
        assertNotNull(studentQA);
        assertNotNull(studentQA2);
    }

    @Test
    public void StudentSetterNameTest() {
        dummyStudent.setName("Camila");
        assertEquals("Camila", dummyStudent.getName());
    }

    @Test
    public void StudentGetterNameTest() {
        assertEquals("Fran", dummyStudent.getName());
    }

    @Test
    void shouldBeCorrectInstantiated(){
        assertEquals("09003688800", dummyStudent.getUsn());
        assertEquals("Fran", dummyStudent.getName());
    }

    @Test
    void shouldBeTheSameObject(){
        assertEquals(dummyStudent, dummyStudent2);
    }


}