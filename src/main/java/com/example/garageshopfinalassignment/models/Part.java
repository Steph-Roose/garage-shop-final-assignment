package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="parts")
public class Part {
    @Id
    @GeneratedValue
    private Long id;

    private String partName;
    private double unitPrice;

// relationships
    @ManyToMany(mappedBy = "usedParts")
    private List<Log> logs;

// constructors
    public Part() {
    }

    public Part(String partName, double unitPrice) {
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
    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
