package com.example.demo.repositories;

import com.example.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT a FROM Product a WHERE a.id LIKE 'PD%' ORDER BY a.id DESC")
    List<Product> findTopByIdOrderByIdDesc();
}
