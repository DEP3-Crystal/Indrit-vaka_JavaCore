package com.crystal.atm.model.account;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Account {
    private final String IBAN;
    private double balance;
    private final List<Transaction> transactions;
    private final List<Card> cards;

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void deposit(String description, String reference, double cent) {
        balance += cent;
        Transaction transaction = new Deposit(description, reference, cent);
        addTransaction(transaction);
    }
    public void withdraw(String description, String reference, double cent) {
        //TODO Validation
        balance -= cent;
        Transaction transaction = new Withdraw(description, reference, cent);
        addTransaction(transaction);
    }
    public Account(String IBAN, double balance) {
        this.IBAN = IBAN;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        this.cards = new ArrayList<>();
    }
    public Account(String IBAN) {
        this(IBAN, 0);
    }
}
