package com.crystal.atm.dao;

import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.user.User;

import java.util.Map;

public class DataFromFile implements DataAccess {
    @Override
    public Map<Integer, User> getUsers() {
        return null;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addUsers(Map<Integer, User> people) {

    }

    @Override
    public Map<String, Card> getCards() {
        return null;
    }

    @Override
    public Map<String, Account> getAccounts() {
        return null;
    }

    @Override
    public void addAccount(Account accounts) {

    }
}
