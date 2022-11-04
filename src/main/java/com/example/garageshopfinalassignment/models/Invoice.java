package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    static final private String TAX_PERCENTAGE = "21%";
    private double costBeforeTax;
    private double costAfterTax;
    private boolean paid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private List<Log> finishedLogs;

    public Invoice() {
    }

    public Invoice(double costBeforeTax, double costAfterTax, boolean paid, Customer customer) {
        this.costBeforeTax = costBeforeTax;
        this.costAfterTax = costAfterTax;
        this.paid = paid;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getTAX_PERCENTAGE() {
        return TAX_PERCENTAGE;
    }

    public double getCostBeforeTax() {
        return costBeforeTax;
    }

    public double getCostAfterTax() {
        return costAfterTax;
    }

    public boolean isPaid() {
        return paid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Log> getFinishedLogs() {
        return finishedLogs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCostBeforeTax(double costBeforeTax) {
        this.costBeforeTax = costBeforeTax;
    }

    public void setCostAfterTax(double costAfterTax) {
        this.costAfterTax = costAfterTax;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setFinishedLogs(List<Log> finishedLogs) {
        this.finishedLogs = finishedLogs;
    }
}
