package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.CarDto;
import com.example.garageshopfinalassignment.dtos.LogDto;
import com.example.garageshopfinalassignment.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // endpoints
    @PostMapping("/cars")
    public ResponseEntity<Object> addCar(@Valid @RequestBody CarDto dto, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }

        return ResponseEntity.created(null).body(carService.addCar(dto));
    }

    @GetMapping("/cars/{customerId}")
    public ResponseEntity<Object> getCarByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok().body(carService.findCarByCustomerId(customerId));
    }

    @PatchMapping("/cars/{id}/documents")
    public ResponseEntity<Object> addCarDocuments(@PathVariable("id") Long id, @RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(carService.addCarDocumentsToCar(id, file));
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable("id") Long id, @RequestBody CarDto dto) {
        return ResponseEntity.ok().body(carService.updateCar(id, dto));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(carService.deleteCar(id));
    }
    // add a log

    // delete a log
}
