package com.example.demo.repository;

import com.example.demo.domain.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {
    @Query("SELECT a FROM ProductVariant a WHERE a.id LIKE 'PDV%' ORDER BY a.id DESC")
    List<ProductVariant> findTopByIdOrderByIdDesc();
}
