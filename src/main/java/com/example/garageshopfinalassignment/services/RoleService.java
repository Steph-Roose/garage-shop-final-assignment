package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.RoleDto;
import com.example.garageshopfinalassignment.models.Role;
import com.example.garageshopfinalassignment.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepos;

    // constructor
    public RoleService(RoleRepository roleRepos) {
        this.roleRepos = roleRepos;
    }

    // methods
    public RoleDto createRole(RoleDto dto) {
        Role role = new Role();
        role.setRolename(dto.getRolename());
        roleRepos.save(role);

        RoleDto newDto = new RoleDto();
        newDto.setRolename(role.getRolename());

        return newDto;
    }

}
