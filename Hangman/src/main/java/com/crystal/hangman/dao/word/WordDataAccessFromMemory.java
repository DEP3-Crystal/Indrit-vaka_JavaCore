package com.crystal.hangman.dao.word;

import java.util.Map;

public class WordDataAccessFromMemory implements WordDataAccess {
    Map<String, String> words;

    @Override
    public Map<String, String> getWords() {
        if (words == null) {
            words = loadWords();
        }
        return words;
    }

    private Map<String, String> loadWords() {
        return Map.of("arrangement", "a plan or preparation for a future event",
                "attempt", "make an effort to achieve or complete",
                "brick", "a small rectangular block typically made of fired or sun-dried clay, used in building");
    }


}
