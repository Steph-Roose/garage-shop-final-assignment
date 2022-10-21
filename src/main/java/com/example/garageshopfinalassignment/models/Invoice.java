package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="invoices")
public class Invoice {
    @Id
    @GeneratedValue
    Long id;

    private double taxPercentage;
    private double costBeforeTax;
    private double costAfterTax;
    private boolean paid;

// relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private List<Log> finishedLogs;

// constructors
    public Invoice() {
    }

    public Invoice(Long id, double taxPercentage, double costBeforeTax, double costAfterTax, boolean paid) {
        this.id = id;
        this.taxPercentage = taxPercentage;
        this.costBeforeTax = costBeforeTax;
        this.costAfterTax = costAfterTax;
        this.paid = paid;
    }

// getters
    public Long getId() {
        return id;
    }

    public double getTaxPercentage() {
        return taxPercentage;
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

// setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
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
}
