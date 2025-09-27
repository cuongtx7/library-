package com.example.demo.repository;

import com.example.demo.domain.Account;
import com.example.demo.dto.TokenDTO;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);
    @Query("SELECT a FROM Account a WHERE a.id LIKE 'AC%' ORDER BY a.id DESC")
    List<Account> findTopByIdOrderByIdDesc();

    @Query("""
    SELECT a
    FROM Account a
    WHERE (lower(a.username) = lower(:username)
    OR lower(a.email) = lower(:email))
    AND a.isDelete = :isDelete
""")
    Account findByUsernameOrEmailAndIsDelete(
            @Param("username") String username,
            @Param("email") String email,
            @Param("isDelete") Boolean isDelete
    );
    @Query("""
    SELECT new com.example.demo.dto.TokenDTO(
        a.id,
        a.username,
        a.role
        )
    FROM Account a
    WHERE a.id = :id
    """)

    TokenDTO getAccountById(@Param("id") String id);
}
