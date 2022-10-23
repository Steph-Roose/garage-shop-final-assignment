package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.models.Action;
import com.example.garageshopfinalassignment.repositories.ActionRepository;
import org.springframework.stereotype.Service;

@Service
public class ActionService {
    private final ActionRepository actionRepos;

// constructor
    public ActionService(ActionRepository actionRepos) {
        this.actionRepos = actionRepos;
    }

// methods

    public Action toAction(ActionDto dto) {
        var action = new Action();

        action.setActionName(dto.getActionName());
        action.setActionCost(dto.getActionCost());

        return action;
    }

    public ActionDto toActionDto(Action action) {
        ActionDto dto = new ActionDto();

        dto.setId(action.getId());
        dto.setActionName(action.getActionName());
        dto.setActionCost(action.getActionCost());

        return dto;
    }
}
