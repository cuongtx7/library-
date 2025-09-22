package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String idCategory;
    private String nameCategory;
    private String descriptionCategory;
    private String createdBy;
    private LocalDateTime createdDate ;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate ;
}
