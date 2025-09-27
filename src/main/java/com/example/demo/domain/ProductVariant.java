package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_variant")
public class ProductVariant {
    @Id
    @Size(max = 60)
    @Column(name = "id", nullable = false, length = 60)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Size(max = 60)
    @Column(name = "\"size\"", length = 60)
    private String size;

    @Size(max = 60)
    @Column(name = "color", length = 60)
    private String color;

    @Size(max = 60)
    @Column(name = "gender", length = 60)
    private String gender;

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

}