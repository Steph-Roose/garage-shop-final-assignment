package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.dtos.PartDto;
import com.example.garageshopfinalassignment.models.Part;
import com.example.garageshopfinalassignment.services.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    // endpoints
    @PostMapping("/parts")
    public ResponseEntity<Object> addPart(@Valid @RequestBody PartDto dto, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }

        return ResponseEntity.created(null).body(partService.addPart(dto));
    }

    @GetMapping("/parts")
    public ResponseEntity<Object> getAllParts() {
        return ResponseEntity.ok().body(partService.getAllParts());
    }

    @GetMapping("/parts/{id}")
    public ResponseEntity<Object> getPart(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(partService.getPartById(id));
    }

    @PutMapping("/parts/{id}")
    public ResponseEntity<Object> updatePart(@PathVariable("id") Long id, @RequestBody PartDto dto) {
        return ResponseEntity.ok().body(partService.updatePart(id, dto));
    }

    @DeleteMapping("/parts/{id}")
    public ResponseEntity<Object> deletePart(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(partService.deletePart(id));
    }
}
