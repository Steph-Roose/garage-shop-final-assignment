package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.CustomerDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Customer;
import com.example.garageshopfinalassignment.repositories.CarRepository;
import com.example.garageshopfinalassignment.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepos;
    private final CarRepository carRepos;

// constructor
    public CustomerService(CustomerRepository customerRepos, CarRepository carRepos) {
        this.customerRepos = customerRepos;
        this.carRepos = carRepos;
    }

// methods
    public CustomerDto addCustomer(CustomerDto dto) {
        Customer customer = toCustomer(dto);
        customerRepos.save(customer);

        return toCustomerDto(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepos.findAll();
        return customerListToDtoList(customerList);
    }

    public CustomerDto getCustomerById(Long id) {
        if(customerRepos.findById(id).isPresent()) {
            Customer customer = customerRepos.findById(id).get();

            return  toCustomerDto(customer);
        } else {
            throw new RecordNotFoundException("Couldn't find customer");
        }
    }

    public CustomerDto updateCustomer(Long id, CustomerDto dto) {
        if(customerRepos.findById(id).isPresent()) {
            Customer customer = customerRepos.findById(id).get();
            Customer customer1 = toCustomer(dto);
            customer1.setId(customer.getId());

            customerRepos.save(customer1);

            return toCustomerDto(customer1);
        } else {
            throw new RecordNotFoundException("Couldn't find customer");
        }
    }

    public String deleteCustomer(Long id) {
        if(customerRepos.findById(id).isPresent()) {
            customerRepos.deleteById(id);

            return "Customer deleted";
        } else {
            throw new RecordNotFoundException("Couldn't find customer");
        }
    }

    public CustomerDto addCarToCustomer(Long id, Long carId) {
        var optCustomer = customerRepos.findById(id);
        var optCar = carRepos.findById(carId);

        if(optCustomer.isPresent() && optCar.isPresent()) {
            var customer = optCustomer.get();
            var car = optCar.get();

            customer.setCar(car);
            customerRepos.save(customer);

            return toCustomerDto(customer);
        } else {
            throw new RecordNotFoundException();
        }
    }
    // add an invoice

    // delete an invoice

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
