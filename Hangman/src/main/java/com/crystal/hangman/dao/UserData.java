package com.crystal.hangman.dao;

import com.crystal.hangman.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserData {
    private static final String path = "Hangman/src/main/resources/users.txt";

    public static void saveUsers(List<User> users) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(users.stream().map(u -> u + "\n").collect(Collectors.joining()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(path))) {

            Function<String, User> stringToUser = line -> {
                String[] parts = line.split(",");
                String nickName = parts[0];
                int totalScore = Integer.parseInt(parts[1]);
                int score = Integer.parseInt(parts[2]);
                String password = parts[3];
                User user = new User(nickName, password);
                user.setTotalScore(totalScore);
                user.setScore(score);
                return user;
            };
            users = lines.map(stringToUser).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
