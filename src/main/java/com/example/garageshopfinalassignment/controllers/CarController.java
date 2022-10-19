package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.services.CarService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
}
