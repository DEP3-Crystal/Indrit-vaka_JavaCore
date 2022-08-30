package com.crystal.hangman.ai;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HangmanBootTest {

    @Test
    void getRandomWord() {
        var hangmanBoot = new HangmanBoot();

        Map<String, String> words = new HashMap<>();
        words.put("arrangement", "a plan or preparation for a future event");
        var result = hangmanBoot.getRandomWord(words);
        Assertions.assertTrue(Arrays.asList(words).contains(result));
    }
}