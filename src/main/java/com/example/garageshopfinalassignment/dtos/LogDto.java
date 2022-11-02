package com.example.garageshopfinalassignment.dtos;

import com.example.garageshopfinalassignment.models.Log;

import java.util.Date;
import java.util.List;

public class LogDto {
    private Long id;
    private Log.LogStatus logStatus;
    private double totalPartsCost;
    private double totalCost;
    private InvoiceDto invoiceDto;
    private Long carId;
    private ActionDto actionDto;

    private List<PartDto> usedPartsDto;

    // constructors
    public LogDto() {
    }

    public LogDto(Long id, Log.LogStatus logStatus, double totalPartsCost, double totalCost, Long carId, ActionDto actionDto) {
        this.id = id;
        this.logStatus = logStatus;
        this.totalPartsCost = totalPartsCost;
        this.totalCost = totalCost;
        this.carId = carId;
        this.actionDto = actionDto;
    }

    // getters
    public Long getId() {
        return id;
    }

    public Log.LogStatus getLogStatus() {
        return logStatus;
    }

    public double getTotalPartsCost() {
        return totalPartsCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public InvoiceDto getInvoiceDto() {
        return invoiceDto;
    }

    public Long getCarId() {
        return carId;
    }

    public ActionDto getActionDto() {
        return actionDto;
    }

    public List<PartDto> getUsedPartsDto() {
        return usedPartsDto;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setLogStatus(Log.LogStatus logStatus) {
        this.logStatus = logStatus;
    }

    public void setTotalPartsCost(double totalPartsCost) {
        this.totalPartsCost = totalPartsCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setInvoiceDto(InvoiceDto invoiceDto) {
        this.invoiceDto = invoiceDto;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setActionDto(ActionDto actionDto) {
        this.actionDto = actionDto;
    }

    public void setUsedPartsDto(List<PartDto> usedPartsDto) {
        this.usedPartsDto = usedPartsDto;
    }
}
