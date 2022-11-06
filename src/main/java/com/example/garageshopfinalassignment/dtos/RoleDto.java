package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotBlank;

public class RoleDto {
    @NotBlank
    public String roleName;

    public RoleDto() {
    }

    public RoleDto(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
