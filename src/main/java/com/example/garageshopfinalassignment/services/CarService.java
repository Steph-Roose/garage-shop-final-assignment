package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.CarDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Car;
import com.example.garageshopfinalassignment.models.File;
import com.example.garageshopfinalassignment.repositories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CarService {
    private final CarRepository carRepos;
    private final FileStorageService fileStorageService;

// constructor
    public CarService(CarRepository carRepos, FileStorageService fileStorageService) {
        this.carRepos = carRepos;
        this.fileStorageService = fileStorageService;
    }

// methods
    public CarDto addCar(CarDto dto) {
        Car car = toCar(dto);
        carRepos.save(car);

        return toCarDto(car);
    }

    public CarDto findCarByCustomerId(Long customerId) {
        Car car = carRepos.findCarByCustomerId(customerId);
        return toCarDto(car);
    }

    public CarDto addCarDocumentsToCar(Long carId, MultipartFile file) throws IOException {

        if(carRepos.findById(carId).isPresent()) {
            File file1 = fileStorageService.storeFile(file);

            Car car = carRepos.findById(carId).get();

            car.setCarDocuments(file1);
            carRepos.save(car);

            return toCarDto(car);
        } else {
            throw new RecordNotFoundException("Couldn't find car");
        }
    }

    public CarDto updateCar(Long id, CarDto dto) {
        if(carRepos.findById(id).isPresent()) {
            Car car = carRepos.findById(id).get();

            Car car1 = toCar(dto);
            car1.setId(car.getId());

            carRepos.save(car1);

            return toCarDto(car1);
        } else {
            throw new RecordNotFoundException("Couldn't find car");
        }
    }

    public String deleteCar(Long id) {
        if(carRepos.findById(id).isPresent()) {
            carRepos.deleteById(id);

            return "Car deleted";
        } else {
            throw new RecordNotFoundException("Couldn't find car");
        }
    }
    // add a log

    // delete a log

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
