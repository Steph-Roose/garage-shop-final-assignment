package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.services.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ActionController {
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    // endpoints
    @PostMapping("/actions")
    public ResponseEntity<Object> addAction(@Valid @RequestBody ActionDto dto, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }

        return ResponseEntity.ok().body(actionService.addAction(dto));
    }

    @GetMapping("/actions")
    public ResponseEntity<Object> getAllActions() {
        return ResponseEntity.ok().body(actionService.getAllActions());
    }

    @GetMapping("/actions/{id}")
    public ResponseEntity<Object> getAction(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(actionService.getActionById(id));
    }

    @PutMapping("/actions/{id}")
    public ResponseEntity<Object> updateAction(@PathVariable("id") Long id, @RequestBody ActionDto dto) {
        return ResponseEntity.ok().body(actionService.updateAction(id, dto));
    }

    @DeleteMapping("/actions/{id}")
    public ResponseEntity<Object> deleteAction(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(actionService.deleteAction(id));
    }
}
