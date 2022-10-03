//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.view;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.exception.InvalidCurrencyType;
import com.crystal.atm.model.CurrencyType;
import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.account.Transaction;
import com.crystal.atm.model.user.User;
import com.crystal.atm.services.UserService;
import com.crystal.atm.services.account.AccountService;
import com.crystal.atm.services.account.CardService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.crystal.atm.view.ConsoleColors.*;

public class Menu {
    private final DataAccess dataAccess;
    private final UserService userService;
    private final AccountService accountService;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final Map<String, Card> cards;
    private final Map<Integer, User> users;
    private byte attempts = 0;
    Account loggedInAccount;

    public Menu(DataAccess dataAccess, UserService userService, AccountService accountService, OutputManager outputManager, InputManager inputManager) {
        this.userService = userService;
        this.accountService = accountService;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.dataAccess = dataAccess;
        cards = dataAccess.getCards();
        users = dataAccess.getUsers();
    }

    public void showMenu() {
        outputManager.showMessage(TEXT_BLUE + "Please provide the login details");
        loginMenu();
    }

    public void loginMenu() {

        Optional<Card> optionalCard = getCardByCardNumber();
        //we are 100% sure that we got the card
        Card card = optionalCard.orElseThrow();

        validatePin(card);

        //now we are 100% that user has longed in
        Card cardLoggedIn = optionalCard.orElseThrow();
        //get the account of that card
        loggedInAccount = accountService.getCorrespondingAccount(dataAccess, cardLoggedIn);
        User longedInUser = userService.getOwnerOfAccount(users, loggedInAccount);
        welcomeMenu(longedInUser);

    }

    //TODO should i leave those here
    private Optional<Card> getCardByCardNumber() {
        Optional<Card> optionalCard;
        String cardNumber;
        do {
            outputManager.showLabel(TEXT_RED + "0. exit\n" + TEXT_BLUE + "Card number:");
            cardNumber = inputManager.getNumbersOnly();
            optionalCard = CardService.getCard(cards, cardNumber);
            if (optionalCard.isEmpty()) {
                outputManager.showErrMessage("Invalid card number!");
                outputManager.showMessage("Please make sure that you have typed card number right!");
            }
            if (cardNumber.equals("0")) {
                exit();
            }
        } while (optionalCard.isEmpty());
        return optionalCard;
    }

    private void validatePin(Card card) {
        String pin;
        do {
            outputManager.showMessage("pin: ");
            pin = inputManager.getNumbersOnly();
            if (!card.getPin().equals(pin)) {
                outputManager.showErrMessage("Invalid pin!");
                outputManager.showMessage("Please try again!");
            }
            //if there are made 3 attempts already we block the card
            if (attempts >= 3) {
                //TODO block the card
                outputManager.showErrMessage("You have archived the limit attempts. Exiting...");
                exit();
            }
            attempts++;

        } while (!card.getPin().equals(pin) && attempts <= 3);
    }
    //TODO ------------------------------------------------------

    private void welcomeMenu(User longedInUser) {
        outputManager.showMessage(longedInUser.getFullName() + " Welcome to ATM ");

        showMenuOptions();
    }

    private void showMenuOptions() {
        outputManager.showMessage("Please chose from options below");
        outputManager.showMessage(TEXT_BLUE + "1." + TEXT_RESET + " to view balance");
        outputManager.showMessage(TEXT_BLUE + "2." + TEXT_RESET + " to deposit");
        outputManager.showMessage(TEXT_BLUE + "3." + TEXT_RESET + " to withdraw");
        outputManager.showMessage(TEXT_BLUE + "4." + TEXT_RESET + " to view transactions");
        outputManager.showMessage(TEXT_BLUE + "0." + TEXT_RESET + " to exit");

        int ans = inputManager.getInt();
        handleAnswer(ans);
    }

    private void handleAnswer(int ans) {
        switch (ans) {
            case 1 -> showBalance(loggedInAccount);
            case 2 -> deposit(loggedInAccount);
            case 3 -> withdraw(loggedInAccount);
            case 4 -> showTransactions(loggedInAccount);
            case 0 -> exit();
            default -> {
                outputManager.showErrMessage("Not valid options");
                showMenuOptions();
            }
        }
    }

    private void showTransactions(Account loggedInAccount) {
        List<Transaction> transactions = accountService.getTransactions(loggedInAccount);
        outputManager.showMessage("description\t\t|\treference\t|\ttype\t|\tamount", TEXT_BLUE);
        if (transactions.size() == 0)
            outputManager.showMessage(TEXT_GREEN + "No transactions");
        transactions.forEach(t -> {
                    outputManager.showLabel(TEXT_GREEN + t.getDescription() + "\t|\t");
                    outputManager.showLabel(TEXT_GREEN + t.getReference() + "\t|\t");
                    outputManager.showLabel(TEXT_GREEN + t.getType() + "\t|\t");
                    outputManager.showLabel(TEXT_GREEN + t.getAmount() + "\n");
                }
        );
        outputManager.hr();
        showMenuOptions();
    }

    public void showBalance(Account account) {
        long balance = accountService.getBalance(account);
        outputManager.hr();
        outputManager.showMessage("Your balance is: " + TEXT_BLUE + balance);
        showMenuOptions();
    }

    public void deposit(Account account) {
        outputManager.showMessage("Please give the amount you want to deposit");
        long amount = inputManager.getLong();
        outputManager.showMessage("Please give description for the deposit");
        String description = inputManager.getString();
        outputManager.showMessage("Please give reference for the deposit");
        String reference = inputManager.getString();

        try {
            this.accountService.deposit(account, CurrencyType.EURO, description, reference, amount);
        } catch (InvalidCurrencyType e) {
            outputManager.showErrMessage("deposit failed: Invalid currency type");
        } catch (IllegalArgumentException e) {
            outputManager.showErrMessage("deposit failed: " + e.getMessage());
        }
        showMenuOptions();
    }

    public void withdraw(Account account) {
        outputManager.showMessage("Please give the amount you want to withdraw");
        long amount = inputManager.getLong();
        try {
            this.accountService.withdraw(account, CurrencyType.EURO, "Monthly paycheck", "crystal-system", amount);
        } catch (InvalidCurrencyType e) {
            outputManager.showErrMessage("deposit failed: Invalid currency type");
        } catch (IllegalArgumentException e) {
            outputManager.showErrMessage("deposit failed: " + e.getMessage());
        }
        showMenuOptions();
    }

    public void exit() {
        outputManager.showMessage(TEXT_BG_BLUE + TEXT_BLACK + "\t\tThanks for using owr service, bye!\t\t");
        System.exit(0);
    }
}
