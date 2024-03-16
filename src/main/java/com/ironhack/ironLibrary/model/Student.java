package com.ironhack.ironLibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usn;

    private String name;

    public Student() {

    }
    public Student(Integer usn, String name) {
        this.usn = usn;
        this.name = name;
    }
}
