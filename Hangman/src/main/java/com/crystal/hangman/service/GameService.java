package com.crystal.hangman.service;

import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.dao.word.WordDataAccess;
import com.crystal.hangman.model.GameData;
import com.crystal.hangman.model.User;

import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class GameService {
    private final WordDataAccess wordDataAccess;
    private final Random random = new Random();
    Map<String, String> words;
    private final UserDataAccess userDataAccess;


    public GameService(WordDataAccess wordDataAccess, UserDataAccess userDataAccess) {
        this.wordDataAccess = wordDataAccess;
        this.userDataAccess = userDataAccess;
    }

    /**
     * @return chosen word
     */
    public String getNextLevel() {
        if (words == null) {
            loadData();
        }
        String[] wordKeySet = words.keySet().toArray(String[]::new);
        return wordKeySet[random.nextInt(0, wordKeySet.length)];
    }

    public String getDefinition(String word) {
        if (words == null) {
            loadData();
        }
        return words.get(word);
    }

    private void loadData() {
        words = wordDataAccess.getWords();
    }

    public StringBuilder getDashedWord(String givenWord) {
        StringBuilder inputtedWord = new StringBuilder();
        inputtedWord.append(givenWord.replaceAll("\\w", "-"));
        return inputtedWord;
    }

    /**
     * @param wordToGuess word to be guessed by user
     * @param guessedWord the word that user has guessed
     * @param letter      the given letter by user
     * @return true if letter exist in given word
     */
    public StringBuilder replaceLetters(String wordToGuess, StringBuilder guessedWord, char letter) {

        IntStream.range(0, wordToGuess.length()).forEach(i -> {
            if (wordToGuess.charAt(i) == letter) {
                guessedWord.replace(i, i + 1, letter + "");
            }
        });
        return guessedWord;
    }

    /**
     * @param wordToGuess word to be guessed by user
     * @param word        the word that user has guessed
     * @return true if user has find all the letters(WON)
     */
    public boolean hasWon(String wordToGuess, StringBuilder word) {
        return wordToGuess.contentEquals(word);
    }

    public void resetGame(GameData gameData) {
        gameData.resetData();
    }

    public boolean hasLose(GameData gameData) {
        return gameData.getMistakes() >= gameData.getAllowedMistakes();
    }

    public boolean isUsedLetter(GameData gameData, char letter) {
        return gameData.getUsedLetters().contains(letter);
    }

    public void addUsedLetter(GameData gameData, char answer) {
        gameData.getUsedLetters().add(answer);
    }

    public void updateUserData(User user, GameData gameData) {
        if (user.getHighScore() < gameData.getScore()) {
            user.setHighScore(gameData.getScore());
            userDataAccess.saveUser(user);
        }
    }
}
