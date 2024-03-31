package com.ironhack.ironLibrary.model;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @Setter(AccessLevel.NONE)
    private String isbn;
    private String title;
    private String category;
    private Integer quantity;

}
