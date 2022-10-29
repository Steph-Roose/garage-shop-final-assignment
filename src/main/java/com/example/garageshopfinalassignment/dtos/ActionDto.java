package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ActionDto {
    private Long id;

    @NotBlank
    private String actionName;

    @NotNull
    private double actionCost;

    // constructors
    public ActionDto() {
    }

    public ActionDto(Long id, String actionName, double actionCost) {
        this.id = id;
        this.actionName = actionName;
        this.actionCost = actionCost;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getActionName() {
        return actionName;
    }

    public double getActionCost() {
        return actionCost;
    }

    // setters
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
