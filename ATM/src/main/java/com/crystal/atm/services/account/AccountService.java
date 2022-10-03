//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.services.account;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.exception.InvalidCurrencyType;
import com.crystal.atm.model.CurrencyType;
import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.account.Transaction;

import java.util.List;
import java.util.Map;

public class AccountService {
    public AccountService() {
    }

    public long getBalance(Account account) {
        return account.getBalance();
    }

    public Account getAccount(Card card) {
        throw new RuntimeException("Method not implemented yet");
    }

    public void deposit(Account account, CurrencyType currencyType, String description, String reference, long amount) throws InvalidCurrencyType, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Not valid amount of money: " + amount);
        } else if (!currencyType.equals(account.getCurrencyType())) {
            throw new InvalidCurrencyType("Invalid currency type: " + currencyType);
        } else {
            account.deposit(description, reference, amount);
        }
    }

    private boolean isValidWithdrawalAmount(Account account, long amount) {
        return amount >= 1L && amount < getBalance(account);
    }

    public void withdraw(Account account, CurrencyType currencyType, String description, String reference, long amount) throws InvalidCurrencyType, IllegalArgumentException {
        if (!isValidWithdrawalAmount(account, amount)) {
            throw new IllegalArgumentException("Not valid amount of money: " + amount);
        } else if (!currencyType.equals(account.getCurrencyType())) {
            throw new InvalidCurrencyType("Invalid currency type: " + currencyType);
        } else {
            account.withdraw(description, reference, amount);
        }
    }

    public Account getCorrespondingAccount(DataAccess dataAccess, Card cardLoggedIn) {
        return dataAccess.getAccounts().entrySet().stream()
                .dropWhile(a -> !a.getKey().equals(cardLoggedIn.getIBAN()))
                .takeWhile(a -> a.getKey().equals(cardLoggedIn.getIBAN()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow();
    }

    public List<Transaction> getTransactions(Account account) {
        return account.getTransactions();
    }
}
