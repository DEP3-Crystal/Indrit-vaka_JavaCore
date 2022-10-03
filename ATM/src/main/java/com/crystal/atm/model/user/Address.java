package com.crystal.atm.model.user;

import lombok.Data;

@Data
public class Address {
    private  final int personId;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;

    public Address(int personId, String address, String city, String state, String country, String zip) {
        this.personId = personId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }
}
