package com.example.garageshopfinalassignment.models;

import org.hibernate.annotations.GenericGenerator;

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
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "car")
    private List<Log> logBook;

// constructors
    public Car() {
    }

    public Car(Long id, String brand, String model, String licencePlate) {
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

    public Customer getCustomer() {
        return customer;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public List<Log> getLogBook() {
        return logBook;
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void setLogBook(List<Log> logBook) {
        this.logBook = logBook;
    }
}