package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="logs")
public class Log {
    @Id
    @GeneratedValue
    private Long id;
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

// relationships
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_id")
    private Action action;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "logs_parts",
        joinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "log_id", referencedColumnName = "id"))
    private List<Part> usedParts;

// constructors
    public Log() {
    }

    public Log(LogStatus logStatus, Date createdOn, double totalPartsCost, double totalCost) {
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

    public Invoice getInvoice() {
        return invoice;
    }

    public Car getCar() {
        return car;
    }

    public Action getAction() {
        return action;
    }

    public List<Part> getUsedParts() {
        return usedParts;
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

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setUsedParts(List<Part> usedParts) {
        this.usedParts = usedParts;
    }
}
