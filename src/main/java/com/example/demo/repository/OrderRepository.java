package com.example.demo.repository;

import com.example.demo.domain.OrderTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderTable, String> {
    @Query("SELECT a FROM OrderTable a WHERE a.id LIKE 'OD%' ORDER BY a.id DESC")
    List<OrderTable> findTopByIdOrderByIdDesc();

    Page<OrderTable> findByAccountId(String accoutId, Pageable pageable);

}