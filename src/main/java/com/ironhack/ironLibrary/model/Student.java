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
    private String usn;

    private String name;

    public Student() {

    }

}
