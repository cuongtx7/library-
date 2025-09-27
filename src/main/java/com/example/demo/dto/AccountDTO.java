package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    String id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String fullname;
    private String role;

    private LocalDate birthDay;

    private String address;

    private String phone;

    @NotNull
    private String email;

    private Boolean active;

    private String createdBy;

    private LocalDateTime createdDate;


    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

}
