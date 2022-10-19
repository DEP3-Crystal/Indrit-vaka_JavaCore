package com.crystal.hangman.dao.user;

import com.crystal.hangman.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDataAccessFromMemory implements UserDataAccess {
    Map<String, User> users;

    @Override
    public void saveUser(User user) {
        loadUsers();
        users.put(user.getNickName(), user);
    }

    @Override
    public Optional<User> getUserByNickName(String nickName) {
        loadUsers();
        return users.entrySet().stream().filter(entry -> entry.getKey().equals(nickName))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    @Override
    public Map<String, User> getUsers() {
        loadUsers();
        return users;
    }

    public void loadUsers() {
        if (users == null) {
            users = new HashMap<>();
            User indrit = new User("Indrit", "1234Abc.");
            User luka = new User("luka", "1234Abc.");
            users.put(indrit.getNickName(), indrit);
            users.put(luka.getNickName(), luka);
        }
    }
}
