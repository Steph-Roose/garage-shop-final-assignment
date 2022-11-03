package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.models.*;
import com.example.garageshopfinalassignment.repositories.CarRepository;
import com.example.garageshopfinalassignment.repositories.CustomerRepository;
import com.example.garageshopfinalassignment.repositories.InvoiceRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class InvoiceServiceTest {
    @MockBean
    JwtService jwtService;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @Mock
    InvoiceRepository invoiceRepos;

    @Mock
    CustomerRepository customerRepos;

    @Mock
    CarRepository carRepos;

    @Mock
    LogService logService;

    @Mock
    CustomerService customerService;

    @Mock
    CarService carService;

    @InjectMocks
    InvoiceService invoiceService;

    @Captor
    ArgumentCaptor<Invoice> captor;

    Customer customer;
    Car car;
    Action action1;
    Action action2;
    Log log1;
    Log log2;
    Log log3;
    Invoice invoice1;
    Invoice invoice2;

    @BeforeEach
    void setUp() {
        customer = new Customer(1L, "Max", "Verstappen", "2 Bradbourne Drive", "MK78AT", "Milton Keynes", "0612345678", "max@verstappen.nl");
        car = new Car(1L, "Aston Martin", "DB11", "12-34-56");
        action1 = new Action(1L, "Check-up", 45.00);
        action2 = new Action(2L,"Change battery", 100.00);
        log1 = new Log(1L, Log.LogStatus.PAID, 00.00, 45.00, car, action1);
        log2 = new Log(2L, Log.LogStatus.FINISHED, 00.00, 45.00, car, action1);
        log3 = new Log(3L, Log.LogStatus.FINISHED, 100.00, 200.00, car, action2);
        invoice1 = new Invoice(1L, 45.00, 54.45, true, customer, car);
        invoice2 = new Invoice(2L, 245.00, 296.45, false, customer, car);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void createInvoice() {
        List<Log> finishedLogs = new ArrayList<>();
        finishedLogs.add(log2);
        finishedLogs.add(log3);

        when(customerRepos.findById(1L)).thenReturn(Optional.of(customer));
        when(carRepos.findById(1L)).thenReturn(Optional.of(car));



    }

    @Test
    @Disabled
    void getAllInvoicesByCustomerId() {
        Long customerId = 1L;

    }

    @Test
    @Disabled
    void getInvoiceById() {
    }

    @Test
    @Disabled
    void updateInvoice() {
    }

    @Test
    @Disabled
    void payInvoice() {
    }

    @Test
    @Disabled
    void deleteInvoice() {
    }

    @Test
    @Disabled
    void invoiceListToInvoiceDtoList() {
    }

    @Test
    @Disabled
    void toInvoice() {
    }

    @Test
    @Disabled
    void toInvoiceDto() {
    }
}