package com.crystal.atm.services;

import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.user.User;

import java.util.Map;

public class UserService {
    public User getOwnerOfAccount(Map<Integer, User> users,Account loggedInAccount) {
        return users.entrySet().stream()
                .dropWhile(personEntry -> personEntry.getKey() != loggedInAccount.getUserId())
                .takeWhile(personEntry -> personEntry.getKey() == loggedInAccount.getUserId())
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow();
    }

}
