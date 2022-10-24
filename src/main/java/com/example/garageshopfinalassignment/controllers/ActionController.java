package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.services.ActionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActionController {
    private final ActionService actionService;

// controller
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

// endpoints
    @PostMapping("/actions")
    public ActionDto addAction(@RequestBody ActionDto dto) {
        return actionService.addAction(dto);
    }

    @GetMapping("/actions")
    public List<ActionDto> getAllActions() {
        return actionService.getAllActions();
    }

    @GetMapping("/actions/{id}")
    public ActionDto getAction(@PathVariable("id") Long id) {
        return actionService.getActionById(id);
    }

    @PutMapping("/actions/{id}")
    public ActionDto updateAction(@PathVariable("id") Long id, @RequestBody ActionDto dto) {
        return actionService.updateAction(id, dto);
    }
    // delete action
    @DeleteMapping("/actions/{id}")
    public String deleteAction(@PathVariable("id") Long id) {
        return actionService.deleteAction(id);
    }
}
