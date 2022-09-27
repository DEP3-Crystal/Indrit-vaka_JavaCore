//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.model.account;

import com.crystal.atm.model.CurrencyType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Account {
    private final String IBAN;
    private long balance;
    private final List<Transaction> transactions;
    private final List<Card> cards;
    private CurrencyType currencyType;

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void deposit(String description, String reference, long amount) {
        this.balance += amount;
        Transaction transaction = new Deposit(description, reference, amount);
        this.addTransaction(transaction);
    }

    public void withdraw(String description, String reference, long amount) {
        this.balance -= amount;
        Transaction transaction = new Withdraw(description, reference, amount);
        this.addTransaction(transaction);
    }

    public Account(String IBAN, long balance) {
        this.IBAN = IBAN;
        this.balance = balance;
        this.transactions = new ArrayList();
        this.cards = new ArrayList();
    }

    public Account(String IBAN) {
        this(IBAN, 0L);
    }

}
