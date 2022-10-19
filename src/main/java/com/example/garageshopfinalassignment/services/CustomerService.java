package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepos;

    public CustomerService(CustomerRepository customerRepos) {
        this.customerRepos = customerRepos;
    }
}
