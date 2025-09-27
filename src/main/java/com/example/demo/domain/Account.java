package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Size(max = 50)
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Size(max = 60)
    @Column(name = "username", length = 60)
    private String username;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @Size(max = 50)
    @Nationalized
    @Column(name = "fullname", length = 50)
    private String fullname;

    @Size(max = 255)
    @Column(name = "role")
    private String role;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 255)
    @Column(name = "phone")
    private String phone;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private Boolean active;

    @Size(max = 60)
    @Column(name = "created_by", length = 60)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Size(max = 60)
    @Column(name = "last_modified_by", length = 60)
    private String lastModifiedBy;


    @Column(name = "is_delete")
    private Boolean isDelete;

}