package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionDto actionDto = (ActionDto) o;
        return Double.compare(actionDto.actionCost, actionCost) == 0 && id.equals(actionDto.id) && actionName.equals(actionDto.actionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actionName, actionCost);
    }
}
