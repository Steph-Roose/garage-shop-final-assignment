package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.LogDto;
import com.example.garageshopfinalassignment.dtos.LogInputDto;
import com.example.garageshopfinalassignment.dtos.PartDto;
import com.example.garageshopfinalassignment.dtos.UsedPartsDto;
import com.example.garageshopfinalassignment.models.Log;
import com.example.garageshopfinalassignment.models.Part;
import com.example.garageshopfinalassignment.services.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    // endpoints
    @PostMapping("/logs")
    public ResponseEntity<Object> addLog(@Valid @RequestBody LogInputDto dto, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }

        return ResponseEntity.created(null).body(logService.addLog(dto));
    }

    @GetMapping("/logs")
    public ResponseEntity<Object> getLogsByCarId(@RequestParam(value = "car_id") Long carId) {
        return ResponseEntity.ok().body(logService.getLogsByCarId(carId));
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<Object> getLogById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(logService.getLogById(id));
    }

    @PutMapping("/logs/{id}")
    public ResponseEntity<Object> updateLog(@PathVariable("id") Long id, @RequestBody LogDto dto) {
        return ResponseEntity.ok().body(logService.updateLog(id, dto));
    }

    @PatchMapping("/logs/{id}")
    public ResponseEntity<Object> addUsedParts(@PathVariable("id") Long id, @RequestBody UsedPartsDto dto) {
        return ResponseEntity.ok().body(logService.addUsedParts(id, dto));
    }

    @DeleteMapping("/logs/{id}")
    public ResponseEntity<Object> deleteLog(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(logService.deleteLog(id));
    }
}
