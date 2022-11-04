package com.example.garageshopfinalassignment.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="parts")
public class Part {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String partName;
    private double unitPrice;

// relationships
    @ManyToMany(mappedBy = "usedParts")
    private List<Log> logs;

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

    public List<Log> getLogs() {
        return logs;
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

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
}
