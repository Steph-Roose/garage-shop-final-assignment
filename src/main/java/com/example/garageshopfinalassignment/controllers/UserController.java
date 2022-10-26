package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.UserDto;
import com.example.garageshopfinalassignment.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // endpoints
    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable("id") String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable("id") String username, @RequestBody UserDto dto) {
        return userService.updateUser(username, dto);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") String username) {
        return userService.deleteUser(username);
    }
}
