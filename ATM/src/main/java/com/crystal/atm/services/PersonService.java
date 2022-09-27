package com.crystal.atm.services;

import com.crystal.atm.io.InputManager;
import com.crystal.atm.io.OutputManager;
import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.person.Address;
import com.crystal.atm.model.person.Person;

import java.time.LocalDate;

public class PersonService {
    public static void createPersonFromCLI() {
        //TODO Welcome message
        OutputManager.showMessage("Welcome!");
        //Person details *
        OutputManager.showMessage("Please complete fields bellow to register new person!");

        OutputManager.showLabel("First Name: ");
        String firstName = InputManager.getWordLettersOnly();
        OutputManager.showLabel("Last Name: ");
        String lastName = InputManager.getWordLettersOnly();
        OutputManager.showMessage("BirthDay: ");
        LocalDate bd = InputManager.getDate();
        OutputManager.showLabel("Phone number: ");
        String phoneNumber = InputManager.getNumbersOnly();
        OutputManager.showLabel("email address: ");
        String email = InputManager.getEmail();
        //Address details *
        //TODO Validation
        Address address = new Address("street...", "Tirane", "Albania", "Albania", "1001");
        //Accounts

        Account account = new Account("ACV225478AA");
        Person person = new Person(0, firstName, lastName, bd, phoneNumber, email, address);
        person.addAccount(account);
    }
}
