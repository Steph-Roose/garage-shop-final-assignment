package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.CarDto;
import com.example.garageshopfinalassignment.dtos.CarInputDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Car;
import com.example.garageshopfinalassignment.models.Customer;
import com.example.garageshopfinalassignment.models.File;
import com.example.garageshopfinalassignment.repositories.CarRepository;
import com.example.garageshopfinalassignment.repositories.CustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CarService {
    private final CarRepository carRepos;
    private final CustomerRepository customerRepos;
    private final FileStorageService fileStorageService;

    public CarService(CarRepository carRepos, CustomerRepository customerRepos, FileStorageService fileStorageService) {
        this.carRepos = carRepos;
        this.customerRepos = customerRepos;
        this.fileStorageService = fileStorageService;
    }

    public CarDto addCar(CarInputDto dto) {
        var optionalCustomer = customerRepos.findById(dto.getCustomerId());

        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            var car = new Car();

            car.setBrand(dto.getBrand());
            car.setModel(dto.getModel());
            car.setLicencePlate(dto.getLicencePlate());

            Car carWithId = carRepos.save(car);

            customer.setCar(carWithId);
            customerRepos.save(customer);

            return toCarDto(carWithId);
        } else {
            throw new RecordNotFoundException("Couldn't find customer");
        }
    }

    public CarDto getCarById(Long id) {
        if(carRepos.findById(id).isPresent()) {
            return toCarDto(carRepos.findById(id).get());
        } else {
            throw new RecordNotFoundException("Couldn't find car");
        }
    }

    public CarDto addCarDocumentsToCar(Long carId, MultipartFile file) throws IOException {
        if(carRepos.findById(carId).isPresent()) {
            File file1 = fileStorageService.uploadFile(file);
            Car car = carRepos.findById(carId).get();

            car.setCarDocuments(file1);

            return toCarDto(carRepos.save(car));
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
            var car = carRepos.findById(id).get();
            var customer = car.getCustomer();

            customer.setCar(null);
            customerRepos.save(customer);

            carRepos.deleteById(id);

            return "Car deleted";
        } else {
            throw new RecordNotFoundException("Couldn't find car");
        }
    }

    public Car toCar(CarDto dto) {
        var car = new Car();

        car.setId(dto.getId());
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
        if(car.getCarDocuments() != null) {
            dto.setCarDocuments(car.getCarDocuments());
        }

        return dto;
    }
}
