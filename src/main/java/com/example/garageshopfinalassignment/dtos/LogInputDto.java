package com.example.garageshopfinalassignment.dtos;

import java.util.Date;

public class LogInputDto {
    private Long carId;
    private Long actionId;
    private Date createdOn;

    // constructors
    public LogInputDto() {
    }

    public LogInputDto(Long carId, Long actionId, Date createdOn) {
        this.carId = carId;
        this.actionId = actionId;
        this.createdOn = createdOn;
    }

    // getters
    public Long getCarId() {
        return carId;
    }

    public Long getActionId() {
        return actionId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    // setters
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
