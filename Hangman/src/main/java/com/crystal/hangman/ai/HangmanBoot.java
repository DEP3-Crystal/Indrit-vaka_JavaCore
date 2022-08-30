package com.crystal.hangman.ai;

import com.crystal.hangman.dao.GameProperties;
import com.crystal.hangman.dao.LoadWords;
import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.model.GameState;
import com.crystal.hangman.model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

import static com.crystal.hangman.io.ConsoleColors.*;

public class HangmanBoot {

    static GameState gameState;
    static Scanner scanner;
    static HangmanBoot hangmanBoot;
    static int MISTAKE = 0;
    static int allowedMistake;

    static {
        gameState = GameState.IN_PROGRESS;
        scanner = new Scanner(System.in);
        hangmanBoot = new HangmanBoot();
        try {
            allowedMistake = GameProperties.getAllowedMistakes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        int inputtedNumber = OutputManager.showMenu(scanner);
        User user = hangmanBoot.registerUser();
        switch (inputtedNumber) {
            case 1 -> hangmanBoot.startGame(user);
            case 2-> OutputManager.showGameRules();
        }
    }

    public User registerUser() {
        OutputManager.showMessage("Please give a nickName");
        String nickname = InputManager.getWordString(scanner);
        return new User(nickname);
    }

    public void startGame(User user) {
        Map<String, String> words = LoadWords.loadData(Path.of("Hangman/src/main/resources/HangmanWords.txt"));
        Set<Character> usedLetters = new HashSet<>();

        String givenWord = hangmanBoot.getRandomWord(words);
//        givenWord = words.keySet().toArray(String[]::new)[1];
        String definition = words.get(givenWord);

        StringBuilder inputtedWord = new StringBuilder();
        inputtedWord.append(givenWord.replaceAll("\\w", "-"));

        while (gameState == GameState.IN_PROGRESS) {
            OutputManager.hr();
            OutputManager.showMessage(TEXT_BLUE + "Hint: "
                    + TEXT_GREEN + definition);
            OutputManager.showMessage(inputtedWord + "", TEXT_BLUE);

            OutputManager.showMessage("Guess a letter");
            char letter = InputManager.getLetter(scanner).toLowerCase().charAt(0);
            usedLetters.add(letter);

            OutputManager.showMessage("used letters" + usedLetters);

            if (replaceLetters(givenWord, inputtedWord, letter)) {
                user.incrementScore(1);
                boolean won = hasWon(givenWord, inputtedWord);
                if (won) {
                    gameState = GameState.WON;
                    OutputManager.winMessage();
                    break;
                }
            } else {

                OutputManager.showErrMessage("The given letter isn't in the word");
                if (hasLose()) {
                    gameState = GameState.LOSE;
                    OutputManager.loseMessage();
                    break;
                }
                OutputManager.showMessage("You have " + (allowedMistake - MISTAKE) + " chances");

            }
        }
        OutputManager.showMessage("Score: " + TEXT_BLUE + user.getScore());
        OutputManager.showMessage("Mistakes: " + TEXT_RED + MISTAKE);
        gameEnded();
    }


    private void gameEnded() {
        OutputManager.showMessage(TEXT_BLUE + "Do you want to play an other game?"
                + TEXT_RED + " Y/N");
        String ans = InputManager.getLetter(scanner);
        if (ans.equals("y"))
            resetGame();
    }

    private void resetGame() {
        gameState = GameState.IN_PROGRESS;
        MISTAKE = 0;
        startGame(User.currentUser);

    }

    private boolean hasLose() {
        return ++MISTAKE > allowedMistake;
    }

    /**
     * @param wordToGuess word to be guessed by user
     * @param word        the word that user has guessed
     * @return true if user has find all the letters(WON)
     */
    public boolean hasWon(String wordToGuess, StringBuilder word) {
        return wordToGuess.contentEquals(word);
    }

    /**
     * @param wordToGuess word to be guessed by user
     * @param word        the word that user has guessed
     * @param letter      the given letter by user
     * @return true if letter exist in given word
     */
    public boolean replaceLetters(String wordToGuess, StringBuilder word, char letter) {
        IntStream.range(0, wordToGuess.length()).forEach(i -> {
            if (wordToGuess.charAt(i) == letter) {
                word.replace(i, i + 1, letter + "");
            }
        });
        return wordToGuess.contains(letter + "");
    }

    /**
     * @param words List where we will choose the word
     * @return chosen word
     */
    public String getRandomWord(Map<String, String> words) {
        String[] wordKeySet = words.keySet().toArray(String[]::new);
        return wordKeySet[new Random().nextInt(0, wordKeySet.length)];
    }
}
