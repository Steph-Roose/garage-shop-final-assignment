package com.example.garageshopfinalassignment.dtos;

import javax.persistence.Lob;

public class CarDto {
    private Long id;

    private String brand;
    private String model;
    private String licencePlate;

    @Lob
    private byte[] carDocuments;

//constructors
    public CarDto() {
    }

    public CarDto(Long id, String brand, String model, String licencePlate, byte[] carDocuments) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licencePlate = licencePlate;
        this.carDocuments = carDocuments;
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

    public byte[] getCarDocuments() {
        return carDocuments;
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

    public void setCarDocuments(byte[] carDocuments) {
        this.carDocuments = carDocuments;
    }
}
