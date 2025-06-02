package com.example.demo.repository;


import com.example.demo.domain.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, String> {
    List<Librarian> findAllByUsername(String userName);

    @Query("SELECT MAX(c.id) FROM Librarian c WHERE c.id LIKE 'La_%'")
    String findMaxCode();

}
