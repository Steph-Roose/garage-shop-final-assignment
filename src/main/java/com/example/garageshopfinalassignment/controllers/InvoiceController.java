package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.services.InvoiceService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
}
