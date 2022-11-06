package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.*;

public class ActionDto {
    public Long id;

    @NotBlank
    public String actionName;

    @NotNull
    public double actionCost;

    public ActionDto() {
    }

    public ActionDto(Long id, String actionName, double actionCost) {
        this.id = id;
        this.actionName = actionName;
        this.actionCost = actionCost;
    }

    public Long getId() {
        return id;
    }

    public String getActionName() {
        return actionName;
    }

    public double getActionCost() {
        return actionCost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setActionCost(double actionCost) {
        this.actionCost = actionCost;
    }
}
