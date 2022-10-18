package com.crystal.hangman.dao.user;

import com.crystal.hangman.model.User;

import java.util.Map;
import java.util.Optional;

public class UserDataAccessFromMemory implements UserDataAccess {
    Map<String, User> users;

    @Override
    public void addUser(User user) {
        users.put(user.getNickName(), user);
    }

    @Override
    public Optional<User> getUserByNickName(String nickName) {
        // TODO Question
        return users.entrySet().stream().dropWhile(entry-> !entry.getKey().equals(nickName))
                .findFirst()
                .map(Map.Entry::getValue);
//        return users.entrySet().stream().filter(entry-> entry.getKey().equals(nickName))
//                .findFirst()
//                .map(Map.Entry::getValue);
    }

    @Override
    public Map<String, User> getUsers() {
        return null;
    }
}
