package com.crystal.hangman.service;

import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.model.User;

public class UserService {
    private final UserDataAccess userDataAccess;

    public UserService(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    public boolean doesUserExist(String nickname) {
        return userDataAccess.getUserByNickName(nickname).isPresent();
    }

    public User createUser(String nickName, String password) {
        return new User(nickName, password);
    }
    public boolean doesPasswordMatches(String password, User user){
        return password.equals(user.getPassword());
    }


}
