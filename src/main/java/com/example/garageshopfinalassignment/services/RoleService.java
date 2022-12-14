package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.RoleDto;
import com.example.garageshopfinalassignment.models.Role;
import com.example.garageshopfinalassignment.repositories.RoleRepository;

import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepos;

    public RoleService(RoleRepository roleRepos) {
        this.roleRepos = roleRepos;
    }

    public RoleDto createRole(RoleDto dto) {
        Role role = new Role();
        role.setRolename(dto.getRoleName());
        roleRepos.save(role);

        RoleDto newDto = new RoleDto();
        newDto.setRoleName(role.getRolename());

        return newDto;
    }

}
