package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.InvoiceDto;
import com.example.garageshopfinalassignment.dtos.LogDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Invoice;
import com.example.garageshopfinalassignment.models.Log;
import com.example.garageshopfinalassignment.repositories.CarRepository;
import com.example.garageshopfinalassignment.repositories.CustomerRepository;
import com.example.garageshopfinalassignment.repositories.InvoiceRepository;
import com.example.garageshopfinalassignment.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepos;
    private final CustomerRepository customerRepos;

    private final CarRepository carRepos;
    private final LogRepository logRepos;
    private final LogService logService;

    public InvoiceService(InvoiceRepository invoiceRepos, CustomerRepository customerRepos, CarRepository carRepos, LogRepository logRepos, LogService logService) {
        this.invoiceRepos = invoiceRepos;
        this.customerRepos = customerRepos;
        this.carRepos = carRepos;
        this.logRepos = logRepos;
        this.logService = logService;
    }

// methods
    public InvoiceDto createInvoice(InvoiceDto dto) {
        Invoice invoice = toInvoice(dto);

        List<LogDto> finishedLogDtos = logService.getLogsByStatus(invoice.getCar().getId(), Log.LogStatus.FINISHED);
        invoice.setFinishedLogs(logService.logDtoListToLogList(finishedLogDtos));

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

        var optionalCustomer = customerRepos.findById(dto.getCustomerId());
        if(optionalCustomer.isPresent()) {
            var customer = optionalCustomer.get();
            invoice.setCustomer(customer);
        } else {
            throw new RecordNotFoundException("Couldn't find matching customer");
        }

        var optionalCar = carRepos.findById(dto.getCarId());
        if(optionalCar.isPresent()) {
            var car = optionalCar.get();
            invoice.setCar(car);
        } else {
            throw new RecordNotFoundException("Couldn't find matching car");
        }

        return invoice;
    }

    public InvoiceDto toInvoiceDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();

        dto.setId(invoice.getId());
        dto.setTaxPercentage(invoice.getTaxPercentage());
        dto.setCostBeforeTax(invoice.getCostBeforeTax());
        dto.setCostAfterTax(invoice.getCostAfterTax());
        dto.setPaid(invoice.isPaid());
        dto.setCustomerId(invoice.getCustomer().getId());
        dto.setCarId(invoice.getCar().getId());

        return dto;
    }
}
