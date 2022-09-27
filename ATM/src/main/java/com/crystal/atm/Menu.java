//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.exception.InvalidCurrencyType;
import com.crystal.atm.io.InputManager;
import com.crystal.atm.io.OutputManager;
import com.crystal.atm.model.CurrencyType;
import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.person.Person;
import com.crystal.atm.services.account.AccountService;
import com.crystal.atm.services.account.CardService;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static com.crystal.atm.io.ConsoleColors.TEXT_BLUE;
import static com.crystal.atm.io.ConsoleColors.TEXT_RED;

public class Menu {
    private final AccountService accountService = new AccountService();
    Scanner scanner = new Scanner(System.in);
    DataAccess dataAccess;
    Map<String, Card> cards = dataAccess.getCards();
    Map<Integer, Person> people = dataAccess.getPeople();
    Person longedInUser;
    byte attempts = 0;

    public Menu(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public void showMenu() {
        OutputManager.showMessage(TEXT_BLUE + "Please provide the login details");
        logIn();
    }

    public void logIn() {
        String cardNumber;
        Optional<Card> optionalCard;
        do {
            OutputManager.showLabel(TEXT_RED + "0. exit" + TEXT_BLUE + "Account Card number:");
            cardNumber = InputManager.getNumbersOnly();
            optionalCard = CardService.cardExist(cards, cardNumber);
            if (optionalCard.isEmpty()) {
                OutputManager.showErrMessage("Invalid card number!");
                OutputManager.showMessage("Please make sure that you have typed card number right!");
            }
            if (cardNumber.equals("0")) {
                exit();
            }
        } while (optionalCard.isEmpty());
        //we are 100% sure that we got the card
        Card card = optionalCard.orElseThrow();
        String pin;

        do {
            OutputManager.showMessage("pin: ");
            pin = InputManager.getNumbersOnly();
            if (!card.getPin().equals(pin)) {
                OutputManager.showErrMessage("Invalid pin!");
                OutputManager.showMessage("Please try again!");
            }
            //if there are made 3 attempts already we block the card
            if(attempts >=3){
                //TODO block the card
                OutputManager.showErrMessage("You have archived the limit attempts. Exiting...");
                exit();
            }
            attempts++;

        } while (!card.getPin().equals(pin) || attempts <=3);
        //now we are 100% that user has longed in
        longedInUser = loadUser(optionalCard.orElseThrow());
        welcomeMenu();

    }

    private void welcomeMenu() {
        OutputManager.showMessage(longedInUser.getFullName() + " Welcome to ATM ");

    }


    private Person loadUser(Card card) {
        return people.entrySet().stream()
                .takeWhile(personEntry -> personEntry.getKey() == card.getPersonId())
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow();
    }

    public void deposit() {
        Account account = new Account("546a6453158");
        long amount = 1500L;

        try {
            this.accountService.deposit(account, CurrencyType.EURO, "Monthly paycheck", "crystal-system", amount);
        } catch (InvalidCurrencyType e) {
            System.out.println("deposit failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("deposit failed: " + e.getMessage());
        }

    }

    public void withdraw() {
        Account account = new Account("546a6453158");
        long amount = 1500L;

        try {
            this.accountService.withdraw(account, CurrencyType.EURO, "Monthly paycheck", "crystal-system", amount);
        } catch (InvalidCurrencyType e) {
            System.out.println("deposit failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("deposit failed: " + e.getMessage());
        }

    }

    public void exit() {
        System.exit(0);
    }
}
