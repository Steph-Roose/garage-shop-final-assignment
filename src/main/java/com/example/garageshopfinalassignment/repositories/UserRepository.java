package com.example.garageshopfinalassignment.repositories;

import com.example.garageshopfinalassignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
