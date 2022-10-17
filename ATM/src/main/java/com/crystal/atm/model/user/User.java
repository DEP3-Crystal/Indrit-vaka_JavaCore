package com.crystal.atm.model.user;

import com.crystal.atm.model.account.Account;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private final int userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String phoneNumber;
    private String email;
    private Address address;
    // TODO [0]switch to map
    private List<Account> accounts;

    public User(int userId, String firstName, String lastName, LocalDate birthDay, String phoneNumber, String email, Address address) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
