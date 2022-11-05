package com.example.garageshopfinalassignment.dtos;

import com.example.garageshopfinalassignment.models.File;

public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String licencePlate;
    private File carDocuments;

    public CarDto() {
    }

    public CarDto(Long id, String brand, String model, String licencePlate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licencePlate = licencePlate;
    }

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
}
