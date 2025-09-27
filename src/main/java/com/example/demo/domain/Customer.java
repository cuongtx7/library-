package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Size(max = 60)
    @Column(name = "id", nullable = false, length = 60)
    private String id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "birth_day")
    private Instant birthDay;

    @Size(max = 20)
    @Column(name = "gender", length = 20)
    private String gender;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

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