package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.InvoiceDto;
import com.example.garageshopfinalassignment.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // endpoints
    @PostMapping("/invoices")
    public ResponseEntity<Object> createInvoice(@RequestParam(value = "customer_id", required = true) Long customerId) {
        return ResponseEntity.created(null).body(invoiceService.createInvoice(customerId));
    }

    @GetMapping("/invoices")
    public ResponseEntity<Object> getAllInvoicesByCustomerId(@RequestParam(value = "customer_id", required = true) Long customerId) {
        return ResponseEntity.ok().body(invoiceService.getAllInvoicesByCustomerId(customerId));
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<Object> getInvoiceById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(invoiceService.getInvoiceById(id));
    }

    @PutMapping("/invoices/{id}")
    public ResponseEntity<Object> updateInvoice(@PathVariable("id") Long id, @RequestBody InvoiceDto dto) {
        return ResponseEntity.ok().body(invoiceService.updateInvoice(id, dto));
    }

    @PatchMapping("/invoices/{id}")
    public ResponseEntity<Object> payInvoice(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(invoiceService.payInvoice(id));
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(invoiceService.deleteInvoice(id));
    }
}
