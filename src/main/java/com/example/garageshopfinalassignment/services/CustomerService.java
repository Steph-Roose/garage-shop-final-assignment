package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.CustomerDto;
import com.example.garageshopfinalassignment.models.Customer;
import com.example.garageshopfinalassignment.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepos;

// constructor
    public CustomerService(CustomerRepository customerRepos) {
        this.customerRepos = customerRepos;
    }

// methods
    public List<CustomerDto> getCustomers() {
        List<Customer> customerList = customerRepos.findAll();
        return customerListToDtoList(customerList);
    }


    public List<CustomerDto> customerListToDtoList(List<Customer> customers) {
        List<CustomerDto> dtoList = new ArrayList<>();

        for(Customer customer : customers) {
            CustomerDto dto = toCustomerDto(customer);

            dtoList.add(dto);
        }
        return dtoList;
    }

    public Customer toCustomer(CustomerDto dto) {
        var customer = new Customer();

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setAddress(dto.getAddress());
        customer.setPostcode(dto.getPostcode());
        customer.setResidence(dto.getResidence());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());

        return customer;
    }

    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto dto = new CustomerDto();

        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setAddress(customer.getAddress());
        dto.setPostcode(customer.getPostcode());
        dto.setResidence(customer.getResidence());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());

        return dto;
    }

}
