package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotNull;

public class UsedPartsDto {
    @NotNull
    public Long partId;
    public String partName;
    @NotNull
    public int quantity;

    public UsedPartsDto() {
    }

    public UsedPartsDto(Long partId, String partName, int quantity) {
        this.partId = partId;
        this.partName = partName;
        this.quantity = quantity;
    }

    public Long getPartId() {
        return partId;
    }

    public String getPartName() {
        return partName;
    }

    public int getQuantity() {
        return quantity;
    }

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
