package com.example.garageshopfinalassignment.repositories;

import com.example.garageshopfinalassignment.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findCarByCustomerId(Long customerId);
}
