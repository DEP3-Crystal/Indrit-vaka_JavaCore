package com.crystal.hangman.dao.user;

import com.crystal.hangman.model.User;

import java.util.Optional;

public interface UserDataAccess {
    void addUser(User user);
    Optional<User> getUserByNickName(String nickName);
}
