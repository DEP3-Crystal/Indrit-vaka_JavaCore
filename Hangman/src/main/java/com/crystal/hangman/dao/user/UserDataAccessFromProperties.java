package com.crystal.hangman.dao.user;

import com.crystal.hangman.model.User;

import java.util.Optional;

public class UserDataAccessFromProperties implements UserDataAccess {
    @Override
    public void addUser(User user) {

    }

    @Override
    public Optional<User> getUserByNickName(String nickName) {
        return Optional.empty();
    }
}
