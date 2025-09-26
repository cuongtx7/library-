package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "productVariant")
public class ProductVariant extends AbstractAuditingEntity {
    @NotNull
    @Column(name = "id_variant", nullable = false, length = 10)
    private String idVariant;

    @Size(max = 10)
    @NotNull
    @Column(name = "id_product", nullable = false, length = 10)
    private String idProduct;

    @Size(max = 50)
    @NotNull
    @Column(name = "product_code", nullable = false, length = 50)
    private String productCode;

    @Size(max = 10)
    @Nationalized
    @Column(name = "\"size\"", length = 10)
    private String size;

    @Size(max = 50)
    @Nationalized
    @Column(name = "color", length = 50)
    private String color;

    @Size(max = 10)
    @Nationalized
    @Column(name = "gender", length = 10)
    private String gender;

    @NotNull
    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @ColumnDefault("0")
    @Column(name = "discount", precision = 5, scale = 2)
    private BigDecimal discount;

    @ColumnDefault("0")
    @Column(name = "quantity")
    private Integer quantity;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;


    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

}