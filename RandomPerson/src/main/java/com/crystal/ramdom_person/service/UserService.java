package com.crystal.hangman.service;

import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.model.User;

import java.util.Map;
import java.util.Optional;

public class UserService {
    private static UserService instance;
    private final UserDataAccess userDataAccess;

    private UserService(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public static synchronized UserService getInstance(UserDataAccess userDataAccess) {
        if (instance == null) {
            instance = new UserService(userDataAccess);
        }
        return instance;
    }

    public boolean doesUserExist(String nickname) {
        return userDataAccess.getUserByNickName(nickname).isPresent();
    }

    public User createUser(String nickName, String password) {
        return new User(nickName, password);
    }

    public boolean doesPasswordMatches(String password, User user) {
        return password.equals(user.getPassword());
    }


    public Map<String, User> getAllUsers() {
        return userDataAccess.getUsers();
    }

    public void saveUserData(User user) {
        userDataAccess.saveUser(user);
    }

    public void addUser(User user) {
        userDataAccess.saveUser(user);
    }

    public Optional<User> getUserByNickName(String nickName) {
        return userDataAccess.getUserByNickName(nickName);
    }
}
