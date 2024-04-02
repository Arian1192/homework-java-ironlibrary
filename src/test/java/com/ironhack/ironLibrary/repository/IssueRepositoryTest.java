package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Issue;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @BeforeEach
    void setUp() {
        Issue issue = new Issue();
        issueRepository.save(issue);
    }

    @AfterEach
    void tearDown() {
        issueRepository.deleteAll();
    }

    @Test
    void saveItemTest() {
        assertEquals(1, issueRepository.count());
    }

    @Test
    void deleteItemTest() {
        Optional<Issue> firstIssue = issueRepository.findAll().stream().findFirst();
        assertTrue(firstIssue.isPresent());
        issueRepository.delete(firstIssue.get());
        assertEquals(0, issueRepository.count());
    }

}
