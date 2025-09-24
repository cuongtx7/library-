package com.example.demo.repository;

import com.example.demo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT a FROM Customer a WHERE a.id LIKE 'CT%' ORDER BY a.id DESC")
    List<Customer> findTopByIdOrderByIdDesc();

    Optional<Customer> findByPhone(String phone);

    Optional<Customer> findByEmail(String email);

}
