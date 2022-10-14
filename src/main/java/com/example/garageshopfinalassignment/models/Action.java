package com.example.garageshopfinalassignment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actions")
public class Action {
    @Id
    @GeneratedValue
    Long id;

    private String actionName;
    private double actionCost;

    public Action() {
    }

    public Action(Long id, String actionName, double actionCost) {
        this.id = id;
        this.actionName = actionName;
        this.actionCost = actionCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public double getActionCost() {
        return actionCost;
    }

    public void setActionCost(double actionCost) {
        this.actionCost = actionCost;
    }
}
