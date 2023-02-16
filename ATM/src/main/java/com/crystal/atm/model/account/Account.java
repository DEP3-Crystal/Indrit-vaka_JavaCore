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
    /**
     * The account id
     */
    private final String IBAN;
    private final int userId;
    private long balance;
    private final List<Transaction> transactions;
    private final List<Card> cards;
    private final CurrencyType currencyType;

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void deposit(String description, String reference, long amount) {
        this.balance += amount;
        Transaction transaction = new Transaction(description, reference, "deposit", amount);
        this.addTransaction(transaction);
    }

    public void withdraw(String description, String reference, long amount) {
        this.balance -= amount;
        Transaction transaction = new Transaction(description, reference, "withdraw", amount);
        this.addTransaction(transaction);
    }

    public Account(String IBAN, int userId, long balance, CurrencyType currencyType) {
        this.IBAN = IBAN;
        this.userId = userId;
        this.balance = balance;
        this.currencyType = currencyType;
        this.transactions = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public Account(String IBAN, int userId, CurrencyType currencyType) {
        this(IBAN, userId, 0L, currencyType);
    }

}
