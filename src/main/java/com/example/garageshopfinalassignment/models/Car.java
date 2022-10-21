package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private String model;
    private String licencePlate;

// relationships
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="doc_id")
    private File carDocuments;

    @OneToOne(mappedBy = "car")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<Log> logbook;

// constructors
    public Car() {
    }

    public Car(String brand, String model, String licencePlate) {
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

// setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

}