package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.InvoiceDto;
import com.example.garageshopfinalassignment.models.Invoice;
import com.example.garageshopfinalassignment.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepos;

    public InvoiceService(InvoiceRepository invoiceRepos) {
        this.invoiceRepos = invoiceRepos;
    }

// methods
    public List<InvoiceDto> invoiceListToInvoiceDtoList(List<Invoice> invoices) {
        List<InvoiceDto> invoiceDtoList = new ArrayList<>();

        for(Invoice invoice : invoices) {
            InvoiceDto dto = toInvoiceDto(invoice);

            invoiceDtoList.add(dto);
        }
        return invoiceDtoList;
    }
    public Invoice toInvoice(InvoiceDto dto) {
        var invoice = new Invoice();

        invoice.setCostBeforeTax(dto.getCostBeforeTax());
        invoice.setCostAfterTax(dto.getCostAfterTax());
        invoice.setPaid(dto.isPaid());

        return invoice;
    }

    public InvoiceDto toInvoiceDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();

        dto.setId(invoice.getId());
        dto.setTaxPercentage(invoice.getTaxPercentage());
        dto.setCostBeforeTax(invoice.getCostBeforeTax());
        dto.setCostAfterTax(invoice.getCostAfterTax());
        dto.setPaid(invoice.isPaid());

        return dto;
    }
}
