package com.crystal.hangman.ai;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HangmanBootTest {

    @Test
    void getRandomWord() {
        HangmanBoot hangmanBoot = new HangmanBoot();

        Map<String, String> words = new HashMap<>();
        words.put("arrangement", "a plan or preparation for a future event");
        words.put("garage", "a building for housing a motor vehicle or vehicles");
        var result = hangmanBoot.getRandomWord(words);
        Assertions.assertTrue(words.containsKey(result));
    }
}