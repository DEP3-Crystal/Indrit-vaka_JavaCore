package com.crystal.atm.dao;

import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.user.User;

import java.util.Map;

public interface DataAccess {
    Map<Integer, User> getUsers();

    User getUser(int id);

    void addUser(User user);

    void addUsers(Map<Integer, User> people);

    Map<String, Card> getCards();

    Map<String, Account> getAccounts();

    void addAccount(Account accounts);

}
