package com.example.demo.specification;

import com.example.demo.domain.Account;
import com.example.demo.requests.filters.AccountFilters;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> filters(AccountFilters filters) {
        return (root, query, cb) ->{
            Predicate predicate = cb.conjunction();

            predicate =cb.and(predicate,
                    cb.equal(root.get("isDelete"), Boolean.FALSE));
            if(filters.getId() != null && !filters.getId().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("id"), filters.getId()));
            }
            if(filters.getUsername() != null && !filters.getUsername().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("username"), filters.getUsername()));
            }
            if(filters.getFullname() != null && !filters.getFullname().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("fullname"), filters.getFullname()));
            }
            if(filters.getEmail() != null && !filters.getEmail().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("email"), filters.getEmail()));
            }
            if(filters.getRole() != null && !filters.getRole().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("role"), filters.getRole()));
            }
            if(filters.getPhone() != null && !filters.getPhone().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("phone"), filters.getPhone()));
            }
            if(filters.getAddress() != null && !filters.getAddress().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("address"), filters.getAddress()));
            }
            return predicate;
        };
    }
}
