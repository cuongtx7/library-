package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends AbstractAuditingEntity {
//    @NotNull
//    @Column(name = "id_category", nullable = false, length = 10)
//    private String idCategory;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "name_category", nullable = false, length = 100)
    private String nameCategory;

    @Size(max = 225)
    @Nationalized
    @Column(name = "description_category", length = 225)
    private String descriptionCategory;

}