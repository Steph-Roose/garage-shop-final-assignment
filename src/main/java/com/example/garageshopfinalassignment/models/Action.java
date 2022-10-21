package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="actions")
public class Action {
    @Id
    @GeneratedValue
    private Long id;

    private String actionName;
    private double actionCost;

// relationships
    @OneToMany(mappedBy = "action")
    private List<Log> logs;

// constructors
    public Action() {
    }

    public Action(String actionName, double actionCost) {
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
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setActionCost(double actionCost) {
        this.actionCost = actionCost;
    }
}