package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotBlank;

public class RoleDto {
    @NotBlank
    public String rolename;

    public RoleDto() {
    }

    public RoleDto(String rolename) {
        this.rolename = rolename;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
