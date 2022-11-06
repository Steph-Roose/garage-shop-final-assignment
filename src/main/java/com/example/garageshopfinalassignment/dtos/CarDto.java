package com.example.garageshopfinalassignment.dtos;

public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String licencePlate;

    public String carDocumentsFileName;

    public CarDto() {
    }

    public CarDto(Long id, String brand, String model, String licencePlate, String carDocumentsFileName) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licencePlate = licencePlate;
        this.carDocumentsFileName = carDocumentsFileName;
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

    public String getCarDocumentsFileName() {
        return carDocumentsFileName;
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

    public void setCarDocumentsFileName(String carDocumentsFileName) {
        this.carDocumentsFileName = carDocumentsFileName;
    }
}
