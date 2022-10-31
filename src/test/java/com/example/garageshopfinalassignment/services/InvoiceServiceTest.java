package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.models.Invoice;
import com.example.garageshopfinalassignment.repositories.CarRepository;
import com.example.garageshopfinalassignment.repositories.CustomerRepository;
import com.example.garageshopfinalassignment.repositories.InvoiceRepository;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {
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
    ArgumentCaptor<Invoice> argumentCaptor;

    Invoice invoice1;
    Invoice invoice2;

    @BeforeEach
    void setUp() {
        invoice1 = new Invoice(1L, 45.00, 54.45, false);
        invoice2 = new Invoice(2L, 45.00, 54.45, true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void createInvoice() {
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