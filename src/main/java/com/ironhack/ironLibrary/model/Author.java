package com.ironhack.ironLibrary.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer authorId;
    private String name;
    private String email;
    @OneToOne
    @JoinColumn(name = "isbn")
    private Book authorBook;

    public Author(String name, String email, Book authorBook){
        this.name = name;
        this.email = email;
        this.authorBook = authorBook;
    }
}
