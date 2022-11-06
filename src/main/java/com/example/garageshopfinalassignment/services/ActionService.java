package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.exceptions.BadRequestException;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Action;
import com.example.garageshopfinalassignment.repositories.ActionRepository;

import com.example.garageshopfinalassignment.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionService {
    private final ActionRepository actionRepos;
    private final LogRepository logRepos;

    public ActionService(ActionRepository actionRepos, LogRepository logRepos) {
        this.actionRepos = actionRepos;
        this.logRepos = logRepos;
    }

    public ActionDto addAction(ActionDto dto) {
        Action action = toAction(dto);
        actionRepos.save(action);

        return toActionDto(action);
    }

    public List<ActionDto> getAllActions() {
        return actionListToActionDtoList(actionRepos.findAll());
    }

    public ActionDto getActionById(Long id) {
        if(actionRepos.findById(id).isPresent()) {
            return toActionDto(actionRepos.findById(id).get());
        } else {
            throw new RecordNotFoundException("Couldn't find action");
        }
    }

    public ActionDto updateAction(Long id, ActionDto dto) {
        if(actionRepos.findById(id).isPresent()) {
            Action action = actionRepos.findById(id).get();
            Action action1 = toAction(dto);
            action1.setId(action.getId());

            actionRepos.save(action1);

            return toActionDto(action1);
        } else {
            throw new RecordNotFoundException("Couldn't find action");
        }
    }

    public String deleteAction(Long id) {
        var optionalAction = actionRepos.findById(id);
        if(optionalAction.isPresent()) {
            Action action = optionalAction.get();
            var optionalLogList = logRepos.findByAction_Id(action.getId());

            if(optionalLogList.isEmpty()) {
                String actionName = action.getActionName();
                actionRepos.deleteById(id);

                return "Deleted action: " + actionName;
            } else {
                throw new BadRequestException("Can't delete action. Action is used in " + optionalLogList.size() + " log(s).");
            }
        } else {
            throw new RecordNotFoundException("Couldn't find action");
        }
    }

    public List<ActionDto> actionListToActionDtoList(List<Action> actions) {
        List<ActionDto> actionDtoList = new ArrayList<>();

        for(Action action : actions) {
            ActionDto dto = toActionDto(action);

            actionDtoList.add(dto);
        }
        return actionDtoList;
    }

    public Action toAction(ActionDto dto) {
        var action = new Action();

        if(dto.getId() != null) {
            action.setId(dto.getId());
        }
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
