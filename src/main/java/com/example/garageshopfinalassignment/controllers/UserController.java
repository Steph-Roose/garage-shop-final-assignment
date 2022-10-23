package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.UserDto;
import com.example.garageshopfinalassignment.models.Role;
import com.example.garageshopfinalassignment.models.User;
import com.example.garageshopfinalassignment.repositories.RoleRepository;
import com.example.garageshopfinalassignment.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
    }
    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto.roles) {
            Optional<Role> or = roleRepos.findById(rolename);

            userRoles.add(or.get());
        }

            newUser.setRoles(userRoles);

            userRepos.save(newUser);

            return "Created User: " + newUser.getUsername();
    }
}
