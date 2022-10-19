package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.services.CustomerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
}
