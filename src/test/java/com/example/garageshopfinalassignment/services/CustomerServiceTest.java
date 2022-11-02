package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.CustomerDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Car;
import com.example.garageshopfinalassignment.models.Customer;
import com.example.garageshopfinalassignment.repositories.CustomerRepository;
import com.example.garageshopfinalassignment.security.JwtRequestFilter;
import com.example.garageshopfinalassignment.security.JwtService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class CustomerServiceTest {
    @MockBean
    JwtService jwtService;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @Mock
    CustomerRepository customerRepos;

    @Mock
    CarService carService;

    @InjectMocks
    CustomerService customerService;

    @Captor
    ArgumentCaptor<Customer> captor;

    Customer customer1;
    Customer customer2;
    Customer customer3;
    Car car1;
    Car car2;

    @BeforeEach
    void setUp() {
        customer1 = new Customer(1L, "Max", "Verstappen", "2 Bradbourne Drive", "MK78AT", "Milton Keynes", "0612345678", "max@verstappen.nl");
        customer2 = new Customer(2L, "Charles", "Leclerc", "Via Abetone Inferiore 4", "f41053", "Maranello", "0612345678", "charles@leclerc.nl");
        customer3 = new Customer(3L, "George", "Russell", "7 Operations Centre", "NN13NB", "Brackley", "0612345678", "george@russell.nl");
        car1 = new Car(1L, "Aston Martin", "DB11", "12-34-56");
        car2 = new Car(2L, "Ferrari", "250", "23-45-67");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCustomer() {
        when(customerRepos.save(customer3)).thenReturn(customer3);
        customerService.addCustomer(customerService.toCustomerDto(customer3));
        verify(customerRepos, times(1)).save(captor.capture());
        Customer customer = captor.getValue();

        assertEquals(customer3.getId(), customer.getId());
        assertEquals(customer3.getFirstName(), customer.getFirstName());
        assertEquals(customer3.getLastName(), customer.getLastName());
        assertEquals(customer3.getAddress(), customer.getAddress());
        assertEquals(customer3.getPostcode(), customer.getPostcode());
        assertEquals(customer3.getResidence(), customer.getResidence());
        assertEquals(customer3.getPhone(), customer.getPhone());
        assertEquals(customer3.getEmail(), customer.getEmail());
    }

    @Test
    void getAllCustomers() {
        when(customerRepos.findAll()).thenReturn(List.of(customer1, customer2));

        List<Customer> customers = customerRepos.findAll();
        List<CustomerDto> dtos = customerService.getAllCustomers();

        assertEquals(customers.get(0).getId(), dtos.get(0).getId());
        assertEquals(customers.get(0).getFirstName(), dtos.get(0).getFirstName());
        assertEquals(customers.get(0).getLastName(), dtos.get(0).getLastName());
        assertEquals(customers.get(0).getAddress(), dtos.get(0).getAddress());
        assertEquals(customers.get(0).getPostcode(), dtos.get(0).getPostcode());
        assertEquals(customers.get(0).getResidence(), dtos.get(0).getResidence());
        assertEquals(customers.get(0).getPhone(), dtos.get(0).getPhone());
        assertEquals(customers.get(0).getEmail(), dtos.get(0).getEmail());
    }

    @Test
    void getCustomerById() {
        Long id = 1L;

        when(customerRepos.findById(id)).thenReturn(Optional.of(customer1));

        Customer customer = customerRepos.findById(id).get();
        CustomerDto dto = customerService.getCustomerById(id);

        assertEquals(customer.getFirstName(), dto.getFirstName());
        assertEquals(customer.getLastName(), dto.getLastName());
        assertEquals(customer.getAddress(), dto.getAddress());
        assertEquals(customer.getPostcode(), dto.getPostcode());
        assertEquals(customer.getResidence(), dto.getResidence());
        assertEquals(customer.getPhone(), dto.getPhone());
        assertEquals(customer.getEmail(), dto.getEmail());
    }

    @Test
    void getCustomerByIdThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> customerService.getCustomerById(null));
    }

    @Test
    void updateCustomer() {
        when(customerRepos.findById(2L)).thenReturn(Optional.of(customer2));

        CustomerDto dto = new CustomerDto(2L,"Carlos", "Sainz", "Via Abetone Inferiore 4", "f41053", "Maranello", "0612345678", "carlos@sainz.nl");

        when(customerRepos.save(customerService.toCustomer(dto))).thenReturn(customer2);

        customerService.updateCustomer(2L, dto);

        verify(customerRepos, times(1)).save(captor.capture());

        Customer captured = captor.getValue();

        assertEquals(dto.getId(), captured.getId());
        assertEquals(dto.getFirstName(), captured.getFirstName());
        assertEquals(dto.getLastName(), captured.getLastName());
        assertEquals(dto.getAddress(), captured.getAddress());
        assertEquals(dto.getPostcode(), captured.getPostcode());
        assertEquals(dto.getResidence(), captured.getResidence());
        assertEquals(dto.getPhone(), captured.getPhone());
        assertEquals(dto.getEmail(), captured.getEmail());
    }

    @Test
    void updateCustomerThrowsExceptionTest() {
        CustomerDto dto = new CustomerDto(2L,"Carlos", "Sainz", "Via Abetone Inferiore 4", "f41053", "Maranello", "0612345678", "carlos@sainz.nl");

        assertThrows(RecordNotFoundException.class, () -> customerService.updateCustomer(null, dto));
    }

    @Test
    void deleteCustomer() {
        when(customerRepos.findById(1L)).thenReturn(Optional.of(customer1));

        customerService.deleteCustomer(1L);

        verify(customerRepos).deleteById(1L);
    }

    @Test
    void deleteCustomerThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> customerService.deleteCustomer(null));
    }

    @Test
    @Disabled
    void customerListToDtoList() {
    }

    @Test
    @Disabled
    void toCustomer() {
    }

    @Test
    @Disabled
    void toCustomerDto() {
    }
}