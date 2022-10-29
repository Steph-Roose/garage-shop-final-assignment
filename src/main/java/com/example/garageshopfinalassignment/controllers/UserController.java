package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.UserDto;
import com.example.garageshopfinalassignment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // endpoints
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto dto, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }

        return ResponseEntity.created(null).body(userService.createUser(dto));
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") String username, @RequestBody UserDto dto) {
        return ResponseEntity.ok().body(userService.updateUser(username, dto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String username) {
        return ResponseEntity.ok().body(userService.deleteUser(username));
    }
}
