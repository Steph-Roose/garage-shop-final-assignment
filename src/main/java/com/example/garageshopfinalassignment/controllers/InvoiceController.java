package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.InvoiceDto;
import com.example.garageshopfinalassignment.services.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

// endpoints
    @PostMapping("/invoices")
    public InvoiceDto createInvoice(@RequestBody InvoiceDto dto) {
        return invoiceService.createInvoice(dto);
    }

    @GetMapping("/invoices")
    public List<InvoiceDto> getAllInvoicesByCustomerId(@RequestParam(value = "customer_id", required = true) Long customerId) {
        return invoiceService.getAllInvoicesByCustomerId(customerId);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceDto getInvoiceById(@PathVariable("id") Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @PutMapping("/invoices/{id}")
    public InvoiceDto updateInvoice(@PathVariable("id") Long id, @RequestBody InvoiceDto dto) {
        return invoiceService.updateInvoice(id, dto);
    }

    @DeleteMapping("/invoices/{id}")
    public String deleteInvoice(@PathVariable("id") Long id) {
        return invoiceService.deleteInvoice(id);
    }
}
