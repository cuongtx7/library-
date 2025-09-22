package com.example.demo.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProductDTO {
    private String id;
    private String name;
    private String idCategory;
    private String description;
    private String createdBy;
    private LocalDateTime createdDate ;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate ;

}
