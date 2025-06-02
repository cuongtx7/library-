package com.example.demo.repository;

import com.example.demo.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    @Query("SELECT MAX(c.id) FROM Author c WHERE c.id LIKE 'AU_%'")
    String findMaxCode();
}
