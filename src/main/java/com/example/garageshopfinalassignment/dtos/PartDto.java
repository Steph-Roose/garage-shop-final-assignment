package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PartDto {
    private Long id;
    @NotBlank
    private String partName;
    @NotNull
    private double unitPrice;

    public PartDto() {
    }

    public PartDto(Long id, String partName, double unitPrice) {
        this.id = id;
        this.partName = partName;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public String getPartName() {
        return partName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

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
