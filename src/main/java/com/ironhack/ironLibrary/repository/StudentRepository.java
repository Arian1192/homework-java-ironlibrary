package com.ironhack.ironLibrary.repository;

import com.ironhack.ironLibrary.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findByUsnAndName(String usn, String name);
    Optional<Student> findByUsn(String usn);

}
