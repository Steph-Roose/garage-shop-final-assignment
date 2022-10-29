package com.example.garageshopfinalassignment.dtos;

public class UsedPartsDto {
    private Long partId;

    private int quantity;

    // constructors

    public UsedPartsDto() {
    }

    public UsedPartsDto(Long partId, int quantity) {
        this.partId = partId;
        this.quantity = quantity;
    }

    // getters

    public Long getPartId() {
        return partId;
    }

    public int getQuantity() {
        return quantity;
    }

    // setters

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
