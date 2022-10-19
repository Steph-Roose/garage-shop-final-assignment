package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepos;

    public InvoiceService(InvoiceRepository invoiceRepos) {
        this.invoiceRepos = invoiceRepos;
    }
}
