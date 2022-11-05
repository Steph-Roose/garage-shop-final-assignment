package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {
    @Size(min = 5, max = 8)
    public String username;
    @Size(min = 10)
    public String password;
    public List<String> roles;

    public UserDto() {
    }

    public UserDto(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

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
