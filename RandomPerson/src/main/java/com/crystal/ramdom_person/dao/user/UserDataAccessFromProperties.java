package com.crystal.hangman.dao.user;

import com.crystal.hangman.model.User;
import com.crystal.hangman.secirity.Validation;
import com.crystal.path.ResourcesPath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class UserDataAccessFromProperties implements UserDataAccess {
    private final Properties properties = new Properties();
    private final String path;
    private final Validation validation = Validation.getInstance();
    private Map<String, User> users;

    public UserDataAccessFromProperties() {
        path = ResourcesPath.getResourcePathAsString(this.getClass()) + "users.properties";
    }

    @Override
    public void saveUser(User user) {
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            if (users.containsKey(user.getNickName())) {
                properties.put(user.getNickName(), user.toString());
            } else {
                users.put(user.getNickName(), user);
                users.values().forEach(u -> properties.put(u.getNickName(), u.toString()));
            }
            properties.store(outputStream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<User> getUserByNickName(String nickName) {
        loadData();
        return users.entrySet().stream().filter(kv -> kv.getKey().equals(nickName)).map(Map.Entry::getValue).findFirst();
    }

    @Override
    public Map<String, User> getUsers() {
        loadData();
        return users;
    }

    private void loadData() {
        if (users == null) {
            createFile();
            try (FileInputStream inputStream = new FileInputStream(path)) {
                properties.load(inputStream);

                users = properties.entrySet().stream()
                        .map(kv -> {

                            String[] entries = ((String) kv.getValue()).split(",");

                            String name = (String) kv.getKey();
                            String password = entries[1];
                            if (!validation.isValidNumber(entries[0]))
                                throw new NumberFormatException("Invalid entry for user: " + kv.getKey()
                                        + " entry: " + entries[0] + " is not a valid number");

                            int hihScore = Integer.parseInt(entries[0]);

                            return User.builder()
                                    .nickName(name)
                                    .highScore(hihScore)
                                    .password(password)
                                    .build();

                        })
                        .collect(Collectors.toMap(User::getNickName, u -> u));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createFile() {
        try {
            new File(path).createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
