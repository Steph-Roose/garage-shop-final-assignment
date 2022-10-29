package com.example.garageshopfinalassignment.dtos;

import com.example.garageshopfinalassignment.models.File;

import javax.validation.constraints.Size;
import java.util.List;

public class CarDto {
    private Long id;
    @Size(min = 2, max = 30, message = "Brand should have at least 2 characters")
    private String brand;
    @Size(min = 2, max = 30, message = "Model should have at least 2 characters")
    private String model;
    @Size(min = 8, max = 8, message = "Licence plate must be 8 characters")
    private String licencePlate;
    private File carDocuments;
    private CustomerDto customerDto;
    private List<LogDto> logBookDto;

    //constructors
    public CarDto() {
    }

    public CarDto(Long id, String brand, String model, String licencePlate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licencePlate = licencePlate;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public File getCarDocuments() {
        return carDocuments;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public List<LogDto> getLogBookDto() {
        return logBookDto;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void setCarDocuments(File carDocuments) {
        this.carDocuments = carDocuments;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public void setLogBookDto(List<LogDto> logBookDto) {
        this.logBookDto = logBookDto;
    }
}
