package com.example.garageshopfinalassignment.dtos;

import java.util.List;

public class UserDto {
    public String username;

    public String password;
    public List<String> roles;

// constructors
    public UserDto() {
    }

    public UserDto(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

// getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

// setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
