package com.example.garageshopfinalassignment.dtos;

import java.util.List;

public class PartDto {
    private Long id;
    private String partName;
    private double unitPrice;

// constructors
    public PartDto() {
    }

    public PartDto(Long id, String partName, double unitPrice) {
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
