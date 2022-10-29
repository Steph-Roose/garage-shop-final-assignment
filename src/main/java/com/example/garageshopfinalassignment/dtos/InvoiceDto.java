package com.example.garageshopfinalassignment.dtos;

import java.util.List;

public class InvoiceDto {
    private Long id;
    private double taxPercentage;
    private double costBeforeTax;
    private double costAfterTax;
    private boolean paid;
    private CustomerDto customerDto;
    private CarDto carDto;
    private List<LogDto> finishedLogsDto;

    // constructors
    public InvoiceDto() {
    }

    public InvoiceDto(Long id, double taxPercentage, double costBeforeTax, double costAfterTax, boolean paid, CustomerDto customerDto, CarDto carDto) {
        this.id = id;
        this.taxPercentage = taxPercentage;
        this.costBeforeTax = costBeforeTax;
        this.costAfterTax = costAfterTax;
        this.paid = paid;
        this.customerDto = customerDto;
        this.carDto = carDto;
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

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public CarDto getCarDto() {
        return carDto;
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

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public void setFinishedLogsDto(List<LogDto> finishedLogsDto) {
        this.finishedLogsDto = finishedLogsDto;
    }
}
