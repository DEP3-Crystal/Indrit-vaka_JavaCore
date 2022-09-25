package com.crystal.atm.model.person;

import lombok.Data;

@Data
public class Address {
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;

    public Address(String address, String city, String state, String country, String zip) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }
}
