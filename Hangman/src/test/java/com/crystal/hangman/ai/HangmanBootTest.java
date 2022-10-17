package com.crystal.hangman.ai;

import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class HangmanBootTest {

    @Test
    void getRandomWord(){
        UserService userService = new UserService(dataAccess);
        OutputManager outputManager = new OutputManager();
        InputManager inputManager = new InputManager(new Scanner(System.in),outputManager);
        HangmanBoot hangmanBoot = new HangmanBoot(userService, inputManager, outputManager, dataAccess);
        Map<String, String> words = new HashMap<>();
        words.put("arrangement", "a plan or preparation for a future event");
        words.put("garage", "a building for housing a motor vehicle or vehicles");
        var result = hangmanBoot.getRandomWord(words);
       Assertions.assertTrue(words.containsKey(result));
    }
}