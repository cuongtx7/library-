package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Size(max = 60)
    @Column(name = "id", nullable = false, length = 60)
    private String id;

    @Size(max = 60)
    @Column(name = "name", length = 60)
    private String name;

    @Size(max = 60)
    @Nationalized
    @Column(name = "product_code", length = 60)
    private String productCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_category")
    private Category idCategory;

    @Size(max = 60)
    @Column(name = "description", length = 60)
    private String description;

    @Column(name = "unitPrice", precision = 18, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "discount", precision = 5)
    private BigDecimal discount;

    @Column(name = "quantity")
    private Integer quantity;

    @Size(max = 60)
    @Column(name = "created_by", length = 60)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Size(max = 60)
    @Column(name = "last_modified_by", length = 60)
    private String lastModifiedBy;

    @Column(name = "is_delete")
    private Boolean isDelete;

}