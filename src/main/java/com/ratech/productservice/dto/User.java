package com.ratech.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private String firstName;

    private String lastName;

    private String email;

    private List<Role> roles;
}
