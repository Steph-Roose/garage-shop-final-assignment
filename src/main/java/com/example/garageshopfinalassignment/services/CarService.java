package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepos;

    public CarService(CarRepository carRepos) {
        this.carRepos = carRepos;
    }
}
