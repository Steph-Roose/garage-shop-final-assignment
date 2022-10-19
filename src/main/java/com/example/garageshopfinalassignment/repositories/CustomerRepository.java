package com.example.garageshopfinalassignment.repositories;

import com.example.garageshopfinalassignment.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
