package com.example.garageshopfinalassignment.repositories;

import com.example.garageshopfinalassignment.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllInvoicesByCustomerId(Long customerId);
}
