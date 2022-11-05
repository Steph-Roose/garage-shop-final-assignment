package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotNull;

public class LogInputDto {
    @NotNull
    private Long carId;
    @NotNull
    private Long actionId;

    public LogInputDto() {
    }

    public LogInputDto(Long carId, Long actionId) {
        this.carId = carId;
        this.actionId = actionId;
    }

    public Long getCarId() {
        return carId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }
}
