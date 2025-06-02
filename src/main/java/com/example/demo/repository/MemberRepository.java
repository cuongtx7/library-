package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("SELECT MAX(c.id) FROM Member c WHERE c.id LIKE 'M_%'")
    String findMaxCode();
}
