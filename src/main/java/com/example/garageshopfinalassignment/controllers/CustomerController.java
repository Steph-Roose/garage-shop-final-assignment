package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.CustomerDto;
import com.example.garageshopfinalassignment.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

// endpoints
    @PostMapping("/customers")
    public CustomerDto addCustomer(@RequestBody CustomerDto dto) {
        return customerService.addCustomer(dto);
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/customers/{id}")
    public CustomerDto updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto dto) {
        return customerService.updateCustomer(id, dto);
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        return customerService.deleteCustomer(id);
    }

    @PatchMapping("/customers/{id}/car")
    public CustomerDto addCarToCustomer(@PathVariable("id") Long id, @RequestBody Long carId) {
        return customerService.addCarToCustomer(id, carId);
    }
    // add an invoice

    // delete an invoice
}
