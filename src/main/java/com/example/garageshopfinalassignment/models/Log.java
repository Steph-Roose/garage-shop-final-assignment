package com.example.garageshopfinalassignment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="logs")
public class Log {
    @Id
    @GeneratedValue
    Long id;
    private LogStatus logStatus;
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
    public Log() {
    }

    public Log(Long id, LogStatus logStatus, Date createdOn, double totalPartsCost, double totalCost) {
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

    public LogStatus getLogStatus() {
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

    public void setLogStatus(LogStatus logStatus) {
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
