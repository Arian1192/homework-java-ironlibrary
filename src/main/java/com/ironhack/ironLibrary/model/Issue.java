package com.ironhack.ironLibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "issue")
@Data
@NoArgsConstructor
public class Issue {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issueId;

    @Setter(AccessLevel.NONE)
    private LocalDateTime issueDate;

    @Setter(AccessLevel.NONE)
    private LocalDateTime returnDate;

    @OneToOne
    @JoinColumn(name = "usn")
    private Student issueStudent;
    @OneToOne
    @JoinColumn(name = "isbn")
    private Book issueBook;

    public Issue(Student issueStudent, Book issueBook) {
        this.issueDate = LocalDateTime.now();
        this.returnDate = issueDate.plusDays(7);
        setIssueStudent(issueStudent);
        setIssueBook(issueBook);
    }
}
