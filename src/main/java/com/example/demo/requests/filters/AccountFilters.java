package com.example.demo.requests.filters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountFilters {
    String id;
    String username;
    String fullname;
    String address;
    String role;
    String email;
    String phone;
}
