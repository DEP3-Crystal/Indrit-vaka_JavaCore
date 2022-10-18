package com.crystal.hangman.dao.user;

import com.crystal.hangman.model.User;
import com.crystal.path.ResourcesPath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class UserDataAccessFromProperties implements UserDataAccess {
    private final Properties properties = new Properties();
    private final String path;
    Map<String, User> users;

    public UserDataAccessFromProperties() {
        path = ResourcesPath.getResourcePathAsString(this.getClass()) + "users.properties";
    }

    @Override
    public void addUser(User user) {
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            if (users.containsKey(user.getNickName())) {
                properties.setProperty(user.getNickName(), user.toString());
                properties.store(outputStream, null);
            } else {
                users.values().forEach(u -> properties.put(u.getNickName(), u.toString()));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<User> getUserByNickName(String nickName) {
        return Optional.empty();
    }

    @Override
    public Map<String, User> getUsers() {
        return null;
    }

    private void loadData() {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties.load(inputStream);

            users = properties.entrySet().stream()
                    .map(kv -> {
                        String name = (String) kv.getKey();
                        String[] entries = ((String) kv.getValue()).split(",");
                        int hihScore = Integer.parseInt(entries[0]);
                        String password = entries[1];
                        return User.builder()
                                .nickName(name)
                                .highScore(hihScore)
                                .password(password)
                                .build();
                    })
                    .collect(Collectors.toMap(u -> u.getNickName(), u -> u));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
