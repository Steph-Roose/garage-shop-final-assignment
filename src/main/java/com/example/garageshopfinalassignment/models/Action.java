package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="actions")
public class Action {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String actionName;
    private double actionCost;

    @OneToMany(mappedBy = "action")
    private List<Log> logs;

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