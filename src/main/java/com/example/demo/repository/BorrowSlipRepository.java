package com.example.demo.repository;

import com.example.demo.domain.BorrowSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowSlipRepository extends JpaRepository<BorrowSlip, String> {
    @Query("SELECT MAX(c.id) FROM BorrowSlip c WHERE c.id LIKE 'Slip_%'")
    String findMaxCode();
}
