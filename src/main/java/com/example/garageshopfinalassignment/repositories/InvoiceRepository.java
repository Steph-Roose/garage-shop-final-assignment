package com.example.garageshopfinalassignment.repositories;

import com.example.garageshopfinalassignment.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
