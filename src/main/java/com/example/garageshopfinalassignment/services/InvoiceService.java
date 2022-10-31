package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.InvoiceDto;
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
    private final LogService logService;
    private final CustomerService customerService;
    private final CarService carService;

    public InvoiceService(InvoiceRepository invoiceRepos, CustomerRepository customerRepos, CarRepository carRepos, LogService logService, CustomerService customerService, CarService carService) {
        this.invoiceRepos = invoiceRepos;
        this.customerRepos = customerRepos;
        this.carRepos = carRepos;
        this.logService = logService;
        this.customerService = customerService;
        this.carService = carService;
    }

// methods
    public InvoiceDto createInvoice(Long customerId) {
        Invoice invoice = new Invoice();
        var optionalCustomer = customerRepos.findById(customerId);

        if(optionalCustomer.isPresent()) {
            var customer = optionalCustomer.get();
            var optionalCar = carRepos.findById(customer.getCar().getId());

            if(optionalCar.isPresent()){
                var car = optionalCar.get();
                double totalCost = 0.0;

                List<Log> finishedLogs = logService.logDtoListToLogList(logService.getLogsByStatus(car.getId(), Log.LogStatus.FINISHED));

                for(Log log : finishedLogs) {
                    totalCost += logService.calculateCost(log);
                }

                invoice.setCustomer(customer);
                invoice.setCar(car);
                invoice.setFinishedLogs(finishedLogs);
                invoice.setCostBeforeTax(totalCost);
                invoice.setCostAfterTax(invoice.getCostBeforeTax() * invoice.getTaxPercentage());
                invoice.setPaid(false);
            } else {
                throw new RecordNotFoundException("Couldn't find car");
            }
        } else {
            throw new RecordNotFoundException("Couldn't find customer");
        }

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

    public InvoiceDto payInvoice(Long id) {
        if(invoiceRepos.findById(id).isPresent()) {
            Invoice invoice = invoiceRepos.findById(id).get();
            List<Log> finishedLogs = invoice.getFinishedLogs();

            for(Log log : finishedLogs) {
                log.setLogStatus(Log.LogStatus.PAID);
            }

            invoice.setPaid(true);

            return toInvoiceDto(invoice);
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

        var optionalCustomer = customerRepos.findById(dto.getCustomerDto().getId());
        if(optionalCustomer.isPresent()) {
            var customer = optionalCustomer.get();
            invoice.setCustomer(customer);
        } else {
            throw new RecordNotFoundException("Couldn't find matching customer");
        }

        var optionalCar = carRepos.findById(dto.getCarDto().getId());
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
        dto.setCustomerDto(customerService.toCustomerDto(invoice.getCustomer()));
        dto.setCarDto(carService.toCarDto(invoice.getCar()));

        return dto;
    }
}
