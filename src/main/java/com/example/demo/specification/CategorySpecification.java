package com.example.demo.specification;

import com.example.demo.domain.Category;
import com.example.demo.requests.filters.CategoryFilters;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {
    public static Specification<Category> filters(CategoryFilters filters) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            predicate = cb.and(predicate,
                    cb.equal(root.get("isDelete"), Boolean.FALSE));

            if(filters.getId() != null && !filters.getId().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("id"), filters.getId()));
            }

            if(filters.getName() != null && !filters.getName().isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("name"), filters.getName()));
            }
            return predicate;
        };
    }
}
