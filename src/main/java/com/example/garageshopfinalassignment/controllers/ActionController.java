package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.services.ActionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }
}
