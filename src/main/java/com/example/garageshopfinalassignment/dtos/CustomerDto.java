package com.example.garageshopfinalassignment.dtos;

import javax.validation.constraints.*;
import java.util.Objects;

public class CustomerDto {
    public Long id;
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    public String firstName;
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    public String lastName;
    @NotBlank
    public String address;
    @Size(min = 6, max = 6, message = "Postcode should be 6 characters")
    public String postcode;
    @NotBlank
    public String residence;
    @Size(min = 10, max = 10, message = "Phone number should be 10 characters")
    public String phone;
    @Email
    public String email;
    public CarDto carDto;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String firstName, String lastName, String address, String postcode, String residence, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.residence = residence;
        this.phone = phone;
        this.email = email;
    }

    public CustomerDto(Long id, String firstName, String lastName, String address, String postcode, String residence, String phone, String email, CarDto carDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.residence = residence;
        this.phone = phone;
        this.email = email;
        this.carDto = carDto;
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

    public CarDto getCarDto() {
        return carDto;
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

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id.equals(that.id) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && address.equals(that.address) && postcode.equals(that.postcode) && residence.equals(that.residence) && phone.equals(that.phone) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, postcode, residence, phone, email);
    }
}
