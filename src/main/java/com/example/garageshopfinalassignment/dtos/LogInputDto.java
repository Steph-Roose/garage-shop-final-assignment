package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class LogInputDto {
    @NotNull
    private Long carId;
    @NotNull
    private Long actionId;

    // constructors
    public LogInputDto() {
    }

    public LogInputDto(Long carId, Long actionId) {
        this.carId = carId;
        this.actionId = actionId;
    }

    // getters
    public Long getCarId() {
        return carId;
    }

    public Long getActionId() {
        return actionId;
    }

    // setters
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }
}
