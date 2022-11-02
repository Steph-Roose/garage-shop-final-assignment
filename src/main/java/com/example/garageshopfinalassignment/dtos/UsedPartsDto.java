package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotNull;

public class UsedPartsDto {
    @NotNull
    private Long partId;
    private String partName;
    @NotNull
    private int quantity;

    // constructors
    public UsedPartsDto() {
    }

    public UsedPartsDto(Long partId, String partName, int quantity) {
        this.partId = partId;
        this.partName = partName;
        this.quantity = quantity;
    }

    // getters
    public Long getPartId() {
        return partId;
    }

    public String getPartName() {
        return partName;
    }

    public int getQuantity() {
        return quantity;
    }

    // setters
    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
