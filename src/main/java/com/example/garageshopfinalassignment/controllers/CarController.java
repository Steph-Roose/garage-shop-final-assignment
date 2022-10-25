package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.CarDto;
import com.example.garageshopfinalassignment.models.File;
import com.example.garageshopfinalassignment.services.CarService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // endpoints
    @PostMapping("/cars")
    public CarDto addCar(@RequestBody CarDto dto) {
        return carService.addCar(dto);
    }

    @GetMapping("/cars/{customerId}")
    public CarDto getCarByCustomerId(@PathVariable("customerId") Long customerId) {
        return carService.findCarByCustomerId(customerId);
    }

    @PatchMapping("/cars/{id}/documents")
    public CarDto addCarDocuments(@PathVariable("id") Long id, @RequestBody MultipartFile file) throws IOException {
        return carService.addCarDocumentsToCar(id, file);
    }

    @PutMapping("/cars/{id}")
    public CarDto updateCar(@PathVariable("id") Long id, @RequestBody CarDto dto) {
        return carService.updateCar(id, dto);
    }

    @DeleteMapping("/cars/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        return carService.deleteCar(id);
    }
    // add a log

    // delete a log
}
