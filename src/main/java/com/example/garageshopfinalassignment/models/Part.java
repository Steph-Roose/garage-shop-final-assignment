package com.example.garageshopfinalassignment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parts")
public class Part {
    @Id
    @GeneratedValue
    Long id;

    private String partName;
    private double unitPrice;

// constructors
    public Part() {
    }

    public Part(Long id, String partName, double unitPrice) {
        this.id = id;
        this.partName = partName;
        this.unitPrice = unitPrice;
    }

// getters
    public Long getId() {
        return id;
    }

    public String getPartName() {
        return partName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

// setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
