package com.example.demo.repository;

import com.example.demo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("SELECT MAX(c.id) FROM Category c WHERE c.id LIKE 'ca_%'")
    String findMaxCode();
}
