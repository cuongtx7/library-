package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query("SELECT MAX(c.id) FROM Book c WHERE c.id LIKE 'B_%'")
    String findMaxCode();
}
