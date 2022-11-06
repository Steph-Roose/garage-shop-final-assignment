package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String licencePlate;

    @OneToOne(mappedBy = "car")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileDocument carDocuments;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Log> logBook;

    public Car() {
    }

    public Car(Long id, String brand, String model, String licencePlate) {
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

    public Customer getCustomer() {
        return customer;
    }

    public FileDocument getCarDocuments() {
        return carDocuments;
    }

    public List<Log> getLogBook() {
        return logBook;
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCarDocuments(FileDocument carDocuments) {
        this.carDocuments = carDocuments;
    }

    public void setLogBook(List<Log> logBook) {
        this.logBook = logBook;
    }
}