package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.InvoiceDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
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
    public InvoiceDto createInvoice(InvoiceDto dto) {
        Invoice invoice = toInvoice(dto);
        invoiceRepos.save(invoice);

        return toInvoiceDto(invoice);
    }

    public List<InvoiceDto> getAllInvoicesByCustomerId(Long customerId) {
        List<Invoice> invoiceList = invoiceRepos.findAllInvoicesByCustomerId(customerId);

        if(invoiceList != null) {
            return invoiceListToInvoiceDtoList(invoiceList);
        } else {
            throw new RecordNotFoundException("No invoices found");
        }
    }

    public InvoiceDto getInvoiceById(Long id) {
        if(invoiceRepos.findById(id).isPresent()) {
            Invoice invoice = invoiceRepos.findById(id).get();

            return toInvoiceDto(invoice);
        } else {
            throw new RecordNotFoundException("Couldn't find invoice");
        }
    }

    public InvoiceDto updateInvoice(Long id, InvoiceDto dto) {
        if(invoiceRepos.findById(id).isPresent()) {
            Invoice invoice = invoiceRepos.findById(id).get();
            Invoice invoice1 = toInvoice(dto);
            invoice1.setId(invoice.getId());

            invoiceRepos.save(invoice1);

            return toInvoiceDto(invoice1);
        } else {
            throw new RecordNotFoundException("Couldn't find invoice");
        }
    }

    public String deleteInvoice(Long id) {
        if(invoiceRepos.findById(id).isPresent()) {
            invoiceRepos.deleteById(id);

            return "Invoice deleted";
        } else {
            throw new RecordNotFoundException("Couldn't find invoice");
        }
    }
    // add finished logs to invoice

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
