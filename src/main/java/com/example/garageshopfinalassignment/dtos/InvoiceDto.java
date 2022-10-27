package com.example.garageshopfinalassignment.dtos;

import java.util.List;

public class InvoiceDto {
    private Long id;
    private double taxPercentage;
    private double costBeforeTax;
    private double costAfterTax;
    private boolean paid;
    private Long customerId;
    private Long carId;
    private List<LogDto> finishedLogsDto;

// constructors
    public InvoiceDto() {
    }

    public InvoiceDto(Long id, double taxPercentage, double costBeforeTax, double costAfterTax, boolean paid, Long customerId, Long carId) {
        this.id = id;
        this.taxPercentage = taxPercentage;
        this.costBeforeTax = costBeforeTax;
        this.costAfterTax = costAfterTax;
        this.paid = paid;
        this.customerId = customerId;
        this.carId = carId;
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

    public Long getCustomerId() {
        return customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public List<LogDto> getFinishedLogsDto() {
        return finishedLogsDto;
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

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setFinishedLogsDto(List<LogDto> finishedLogsDto) {
        this.finishedLogsDto = finishedLogsDto;
    }
}
