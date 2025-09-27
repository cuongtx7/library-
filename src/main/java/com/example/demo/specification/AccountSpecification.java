package com.example.demo.specification;

import com.example.demo.domain.Account;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> filters(){
        return (root, query, cb) ->{
            Predicate predicate = cb.conjunction();
            return predicate;
        };
    }
}
