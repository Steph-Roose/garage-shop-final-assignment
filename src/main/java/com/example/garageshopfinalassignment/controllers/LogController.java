package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.LogDto;
import com.example.garageshopfinalassignment.models.Log;
import com.example.garageshopfinalassignment.services.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

// endpoints
    @PostMapping("/logs")
    public LogDto addLog(@RequestBody LogDto dto) {
        return logService.addLog(dto);
    }

    @GetMapping("/logs")
    public List<LogDto> getLogsByCarId(@RequestParam(value = "car_id") Long carId) {
        return logService.getLogsByCarId(carId);
    }

    @GetMapping("/logs/{id}")
    public LogDto getLogById(@PathVariable("id") Long id) {
        return logService.getLogById(id);
    }

    @PutMapping("/logs/{id}")
    public LogDto updateLog(@PathVariable("id") Long id, @RequestBody LogDto dto) {
        return logService.updateLog(id, dto);
    }

    @DeleteMapping("/logs/{id}")
    public String deleteLog(@PathVariable("id") Long id) {
        return logService.deleteLog(id);
    }

    // addUsedParts
}
