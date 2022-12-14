package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.Size;

public class CarInputDto {
    @Size(min = 2, max = 30, message = "Brand should have at least 2 characters")
    public String brand;
    @Size(min = 2, max = 30, message = "Model should have at least 2 characters")
    public String model;
    @Size(min = 8, max = 8, message = "Licence plate must be 8 characters")
    public String licencePlate;
    public Long customerId;

    public CarInputDto() {
    }

    public CarInputDto(String brand, String model, String licencePlate, Long customerId) {
        this.brand = brand;
        this.model = model;
        this.licencePlate = licencePlate;
        this.customerId = customerId;
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

    public Long getCustomerId() {
        return customerId;
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

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
