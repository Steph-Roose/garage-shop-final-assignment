package com.example.garageshopfinalassignment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Invoice() {
    }

    public Invoice(Long id, double taxPercentage, double costBeforeTax, double costAfterTax, boolean paid) {
        this.id = id;
        this.taxPercentage = taxPercentage;
        this.costBeforeTax = costBeforeTax;
        this.costAfterTax = costAfterTax;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public double getCostBeforeTax() {
        return costBeforeTax;
    }

    public void setCostBeforeTax(double costBeforeTax) {
        this.costBeforeTax = costBeforeTax;
    }

    public double getCostAfterTax() {
        return costAfterTax;
    }

    public void setCostAfterTax(double costAfterTax) {
        this.costAfterTax = costAfterTax;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
