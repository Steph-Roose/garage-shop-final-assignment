package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="invoices")
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;

    static final private String TAX_PERCENTAGE = "21%";
    private double costBeforeTax;
    private double costAfterTax;
    private boolean paid;

// relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private List<Log> finishedLogs;

// constructors
    public Invoice() {
    }

    public Invoice(double costBeforeTax, double costAfterTax, boolean paid, Customer customer, Car car) {
        this.costBeforeTax = costBeforeTax;
        this.costAfterTax = costAfterTax;
        this.paid = paid;
        this.customer = customer;
        this.car = car;
    }

// getters
    public Long getId() {
        return id;
    }

    public String getTAX_PERCENTAGE() {
        return TAX_PERCENTAGE;
    }

    public double getCostBeforeTax() {
        return costBeforeTax;
    }

    public double getCostAfterTax() {
        return costAfterTax;
    }

    public boolean isPaid() {
        return paid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public List<Log> getFinishedLogs() {
        return finishedLogs;
    }

// setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCostBeforeTax(double costBeforeTax) {
        this.costBeforeTax = costBeforeTax;
    }

    public void setCostAfterTax(double costAfterTax) {
        this.costAfterTax = costAfterTax;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setFinishedLogs(List<Log> finishedLogs) {
        this.finishedLogs = finishedLogs;
    }
}
