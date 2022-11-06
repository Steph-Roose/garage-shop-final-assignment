package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotBlank;

public class AuthDto {
    @NotBlank
    public String username;

    @NotBlank
    public String password;
}
