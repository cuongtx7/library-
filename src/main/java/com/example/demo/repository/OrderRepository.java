package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT a FROM Order a WHERE a.id LIKE 'OD%' ORDER BY a.id DESC")
    List<Order> findTopByIdOrderByIdDesc();

    Page<Order> findByAccountId(String accoutId, Pageable pageable);

}