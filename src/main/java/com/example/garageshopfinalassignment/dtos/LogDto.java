package com.example.garageshopfinalassignment.dtos;

import com.example.garageshopfinalassignment.models.Log;

import java.util.Date;

public class LogDto {
    private Long id;
    private Log.LogStatus logStatus;
    private Date createdOn;
    private double totalPartsCost;
    private double totalCost;

// enum
    public enum LogStatus {
        NEEDS_APPROVAL,
        SCHEDULED,
        APPROVED,
        FINISHED,
        PAID;
    }

// constructors
    public LogDto() {
    }

    public LogDto(Long id, Log.LogStatus logStatus, Date createdOn, double totalPartsCost, double totalCost) {
        this.id = id;
        this.logStatus = logStatus;
        this.createdOn = createdOn;
        this.totalPartsCost = totalPartsCost;
        this.totalCost = totalCost;
    }

// getters
    public Long getId() {
        return id;
    }

    public Log.LogStatus getLogStatus() {
        return logStatus;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public double getTotalPartsCost() {
        return totalPartsCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

// setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setLogStatus(Log.LogStatus logStatus) {
        this.logStatus = logStatus;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setTotalPartsCost(double totalPartsCost) {
        this.totalPartsCost = totalPartsCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
