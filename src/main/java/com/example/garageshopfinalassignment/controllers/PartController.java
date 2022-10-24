package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.dtos.PartDto;
import com.example.garageshopfinalassignment.models.Part;
import com.example.garageshopfinalassignment.services.PartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

// endpoints
    @PostMapping("/parts")
    public PartDto addPart(@RequestBody PartDto dto) {
        return partService.addPart(dto);
    }

    @GetMapping("/parts")
    public List<PartDto> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/parts/{id}")
    public PartDto getPart(@PathVariable("id") Long id) {
        return partService.getPartById(id);
    }

    @PutMapping("/parts/{id}")
    public PartDto updatePart(@PathVariable("id") Long id, @RequestBody PartDto dto) {
        return partService.updatePart(id, dto);
    }
    // delete action
    @DeleteMapping("/parts/{id}")
    public String deletePart(@PathVariable("id") Long id) {
        return partService.deletePart(id);
    }
}
