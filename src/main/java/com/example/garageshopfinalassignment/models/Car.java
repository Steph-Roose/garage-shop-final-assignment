package com.example.garageshopfinalassignment.models;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue
    Long id;

    private String brand;
    private String model;
    private String licencePlate;

    @Lob
    private byte[] carDocuments;

    public Car() {
    }

    public Car(Long id, String brand, String model, String licencePlate, byte[] carDocuments) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licencePlate = licencePlate;
        this.carDocuments = carDocuments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public byte[] getCarDocuments() {
        return carDocuments;
    }

    public void setCarDocuments(byte[] carDocuments) {
        this.carDocuments = carDocuments;
    }
}
