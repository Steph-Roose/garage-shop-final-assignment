package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.CustomerDto;
import com.example.garageshopfinalassignment.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // endpoints
    @PostMapping("/customers")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerDto dto, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }

        return ResponseEntity.created(null).body(customerService.addCustomer(dto));
    }

    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(customerService.getCustomerById(id));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto dto) {
        return ResponseEntity.ok().body(customerService.updateCustomer(id, dto));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(customerService.deleteCustomer(id));
    }

    @PatchMapping("/customers/{id}/car")
    public ResponseEntity<Object> addCarToCustomer(@PathVariable("id") Long id, @RequestBody Long carId) {
        return ResponseEntity.ok().body(customerService.addCarToCustomer(id, carId));
    }
    // add an invoice

    // delete an invoice
}
