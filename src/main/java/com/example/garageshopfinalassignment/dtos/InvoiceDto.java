package com.example.garageshopfinalassignment.dtos;

import java.util.List;

public class InvoiceDto {
    private Long id;
    private String taxPercentage;
    private double costBeforeTax;
    private double costAfterTax;
    private boolean paid;
    private CustomerDto customerDto;
    private List<LogDto> finishedLogsDto;

    public InvoiceDto() {
    }

    public InvoiceDto(Long id, String taxPercentage, double costBeforeTax, double costAfterTax, boolean paid, CustomerDto customerDto) {
        this.id = id;
        this.taxPercentage = taxPercentage;
        this.costBeforeTax = costBeforeTax;
        this.costAfterTax = costAfterTax;
        this.paid = paid;
        this.customerDto = customerDto;
    }

    public Long getId() {
        return id;
    }

    public String getTaxPercentage() {
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

    public List<LogDto> getFinishedLogsDto() {
        return finishedLogsDto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTaxPercentage(String taxPercentage) {
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

    public void setFinishedLogsDto(List<LogDto> finishedLogsDto) {
        this.finishedLogsDto = finishedLogsDto;
    }
}
