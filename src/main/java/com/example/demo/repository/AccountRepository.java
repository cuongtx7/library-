package com.example.demo.repository;

import com.example.demo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);
    @Query("SELECT a FROM Account a WHERE a.id LIKE 'AC%' ORDER BY a.id DESC")
    List<Account> findTopByIdOrderByIdDesc();

}
