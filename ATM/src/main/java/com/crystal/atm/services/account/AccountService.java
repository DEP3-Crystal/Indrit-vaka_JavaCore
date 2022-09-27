//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.services.account;

import com.crystal.atm.exception.InvalidCurrencyType;
import com.crystal.atm.model.CurrencyType;
import com.crystal.atm.model.account.Account;

public class AccountService {
    public AccountService() {
    }

    public long getBalance(Account account) {
        return account.getBalance();
    }

    public void deposit(Account account, CurrencyType currencyType, String description, String reference, long amount) throws InvalidCurrencyType, IllegalArgumentException {
        if (isNotValidAmount(amount)) {
            throw new IllegalArgumentException("Not valid amount of money: " + amount);
        } else if (!currencyType.equals(account.getCurrencyType())) {
            throw new InvalidCurrencyType("Invalid currency type: " + currencyType);
        } else {
            account.deposit(description, reference, amount);
        }
    }

    private static boolean isNotValidAmount(long amount) {
        return amount >= 1L;
    }

    public void withdraw(Account account, CurrencyType currencyType, String description, String reference, long amount) throws InvalidCurrencyType, IllegalArgumentException {
        if (isNotValidAmount(amount)) {
            throw new IllegalArgumentException("Not valid amount of money: " + amount);
        } else if (!currencyType.equals(account.getCurrencyType())) {
            throw new InvalidCurrencyType("Invalid currency type: " + currencyType);
        } else {
            account.withdraw(description, reference, amount);
        }
    }
}
