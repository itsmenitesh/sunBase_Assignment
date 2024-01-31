package com.sunbase.customerbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String firstName;
    private String lastName;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;

    public void updateFrom(Customer newCustomer) {
        this.firstName = newCustomer.getFirstName();
        this.lastName = newCustomer.getLastName();
        this.street = newCustomer.getStreet();
        this.address = newCustomer.getAddress();
        this.city = newCustomer.getCity();
        this.state = newCustomer.getState();
        this.email = newCustomer.getEmail();
        this.phone = newCustomer.getPhone();
    }
}
