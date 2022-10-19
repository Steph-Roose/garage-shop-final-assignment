package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.services.PartService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }
}
