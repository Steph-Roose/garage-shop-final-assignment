package com.example.garageshopfinalassignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String residence;
    private String phone;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="car_id")
    private Car car;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, String address, String postcode, String residence, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.residence = residence;
        this.phone = phone;
        this.email = email;
    }

    public Customer(Long id, String firstName, String lastName, String address, String postcode, String residence, String phone, String email, Car car) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.residence = residence;
        this.phone = phone;
        this.email = email;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getResidence() {
        return residence;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Car getCar() {
        return car;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
