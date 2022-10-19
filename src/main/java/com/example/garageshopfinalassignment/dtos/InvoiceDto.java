package com.example.garageshopfinalassignment.dtos;

public class InvoiceDto {
    private Long id;
    private double taxPercentage;
    private double costBeforeTax;
    private double costAfterTax;
    private boolean paid;

// constructors
    public InvoiceDto() {
    }

    public InvoiceDto(Long id, double taxPercentage, double costBeforeTax, double costAfterTax, boolean paid) {
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
