package com.crystal.atm.services;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.model.CurrencyType;
import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.user.Address;
import com.crystal.atm.model.user.User;
import com.crystal.atm.view.InputManager;
import com.crystal.atm.view.OutputManagerCli;

import java.time.LocalDate;

public class PersonService {
    public static void createPersonFromCLI(DataAccess dataAccess, InputManager inputManager, OutputManagerCli outputManager) {
        outputManager.showMessage("Welcome!");
        //Person details *
        outputManager.showMessage("Please complete fields bellow to register new person!");

        outputManager.showLabel("\tFirst Name: ");
        String firstName = inputManager.getWordLettersOnly();
        outputManager.showLabel("\tLast Name: ");
        String lastName = inputManager.getWordLettersOnly();
        outputManager.showMessage("\tBirthDay: ");
        LocalDate bd = inputManager.getDate();
        outputManager.showLabel("\tPhone number: ");
        String phoneNumber = inputManager.getNumbersOnly();
        outputManager.showLabel("\temail address: ");
        String email = inputManager.getEmail();
        //Address details *
        int personId = dataAccess.getUsers().size() + 1;
        Address address = new Address(personId, "street...", "Tirane", "Albania", "Albania", "1001");
        //Accounts

        Account account = new Account("ACV225478AA", personId, CurrencyType.EURO);
        User user = new User(0, firstName, lastName, bd, phoneNumber, email, address);
        user.addAccount(account);
    }
}
