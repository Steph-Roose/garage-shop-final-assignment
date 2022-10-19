package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.services.LogService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }
}
