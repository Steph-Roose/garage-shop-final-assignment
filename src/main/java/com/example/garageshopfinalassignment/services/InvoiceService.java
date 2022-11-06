package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.InvoiceDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Car;
import com.example.garageshopfinalassignment.models.Customer;
import com.example.garageshopfinalassignment.models.Invoice;
import com.example.garageshopfinalassignment.models.Log;
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
    private final LogRepository logRepos;
    private final LogService logService;
    private final CustomerService customerService;

    public InvoiceService(InvoiceRepository invoiceRepos, CustomerRepository customerRepos, LogRepository logRepos, LogService logService, CustomerService customerService) {
        this.invoiceRepos = invoiceRepos;
        this.customerRepos = customerRepos;
        this.logRepos = logRepos;
        this.logService = logService;
        this.customerService = customerService;
    }

    public InvoiceDto createInvoice(Long customerId) {
        Invoice invoice = new Invoice();
        var optionalCustomer = customerRepos.findById(customerId);

        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            if(customer.getCar() != null) {
                Car car = customer.getCar();
                double totalInvoiceCost = 0.0;

                List<Log> finishedLogs = logService.logDtoListToLogList(logService.getLogsByStatus(car.getId(), Log.LogStatus.FINISHED.toString()));

                for(Log log : finishedLogs) {
                    totalInvoiceCost += log.getTotalCost();
                }

                invoice.setCustomer(customer);
                invoice.setFinishedLogs(finishedLogs);
                invoice.setCostBeforeTax(totalInvoiceCost);
                invoice.setCostAfterTax(totalInvoiceCost * 1.21);
                invoice.setPaid(false);

                Invoice invoice1 = invoiceRepos.save(invoice);

                for(Log log : finishedLogs) {
                    log.setInvoice(invoice1);
                    logRepos.save(log);
                }

                return toInvoiceDto(invoice1);
            } else {
                throw new RecordNotFoundException("No car registered to customer: " + customer.getFirstName() + " " + customer.getLastName());
            }
        } else {
            throw new RecordNotFoundException("Couldn't find customer");
        }
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
            return toInvoiceDto(invoiceRepos.findById(id).get());
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

    public InvoiceDto payInvoice(Long id) {
        if(invoiceRepos.findById(id).isPresent()) {
            Invoice invoice = invoiceRepos.findById(id).get();
            List<Log> finishedLogs = invoice.getFinishedLogs();
            List<Log> paidLogs = new ArrayList<>();

            for(Log log : finishedLogs) {
                log.setLogStatus(Log.LogStatus.PAID);
                logRepos.save(log);
                paidLogs.add(log);
            }

            invoice.setFinishedLogs(paidLogs);
            invoice.setPaid(true);

            invoiceRepos.save(invoice);

            return toInvoiceDto(invoice);
        } else {
            throw new RecordNotFoundException("Couldn't find invoice");
        }
    }

    public String deleteInvoice(Long id) {
        if(invoiceRepos.findById(id).isPresent()) {
            Invoice invoice = invoiceRepos.findById(id).get();

            invoice.setCustomer(null);

            for(Log log : invoice.getFinishedLogs()) {
                if(log.getInvoice() != null) {
                    log.setInvoice(null);
                    logRepos.save(log);
                }
            }

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

        invoice.setId(dto.getId());
        invoice.setCostBeforeTax(dto.getCostBeforeTax());
        invoice.setCostAfterTax(dto.getCostAfterTax());
        invoice.setPaid(dto.isPaid());
        if(dto.getCustomerDto() != null) {
            invoice.setCustomer(customerService.toCustomer(dto.getCustomerDto()));
        }
        if(!dto.getFinishedLogsDto().isEmpty()) {
            invoice.setFinishedLogs(logService.logDtoListToLogList(dto.getFinishedLogsDto()));
        }

        return invoice;
    }

    public InvoiceDto toInvoiceDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();

        dto.setId(invoice.getId());
        dto.setTaxPercentage(invoice.getTAX_PERCENTAGE());
        dto.setCostBeforeTax(invoice.getCostBeforeTax());
        dto.setCostAfterTax(invoice.getCostAfterTax());
        dto.setPaid(invoice.isPaid());
        dto.setCustomerDto(customerService.toCustomerDto(invoice.getCustomer()));
        dto.setFinishedLogsDto(logService.logListToLogDtoList(invoice.getFinishedLogs()));

        return dto;
    }
}
