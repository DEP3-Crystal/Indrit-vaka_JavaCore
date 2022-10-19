package com.crystal.hangman.dao.user;

import com.crystal.hangman.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserDataAccess {
    void saveUser(User user);

    Optional<User> getUserByNickName(String nickName);

    Map<String, User> getUsers();
}
