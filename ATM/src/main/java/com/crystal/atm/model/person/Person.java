package com.crystal.atm.model.person;

import com.crystal.atm.model.account.Account;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String phoneNumber;
    private String email;
    private Address address;
    private List<Account> accounts;

    public Person(int id, String firstName, String lastName, LocalDate birthDay, String phoneNumber, String email, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.accounts = new ArrayList<>();
    }
    public void addAccount(Account account){
        this.accounts.add(account);
    }
}
