package com.ironhack.ironLibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "issue")
@Data
@NoArgsConstructor
public class Issue {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issueId;
    private String issueDate;
    private String returnDate;
    @OneToOne
    @JoinColumn(name = "usn")
    private Student issueStudent;
    @OneToOne
    @JoinColumn(name = "isbn")
    private Book issueBook;

    public Issue(String issueDate, String returnDate, Student issueStudent, Book issueBook) {
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.issueStudent = issueStudent;
        this.issueBook = issueBook;
    }
}
