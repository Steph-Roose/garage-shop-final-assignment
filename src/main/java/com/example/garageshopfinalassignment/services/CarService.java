package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.CarDto;
import com.example.garageshopfinalassignment.models.Car;
import com.example.garageshopfinalassignment.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepos;

// constructor
    public CarService(CarRepository carRepos, FileStorageService fileStorageService, LogService logService) {
        this.carRepos = carRepos;
    }

// methods
    public Car toCar(CarDto dto) {
        var car = new Car();

        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setLicencePlate(dto.getLicencePlate());

        return car;
    }

    public CarDto toCarDto(Car car) {
        CarDto dto = new CarDto();

        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setLicencePlate(car.getLicencePlate());

        return dto;
    }
}
