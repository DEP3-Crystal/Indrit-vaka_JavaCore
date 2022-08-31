package com.crystal.hangman.ai;

import com.crystal.hangman.dao.GameProperties;
import com.crystal.hangman.dao.LoadWords;
import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.dao.UserData;
import com.crystal.hangman.model.GameState;
import com.crystal.hangman.model.User;
import com.crystal.hangman.secirity.Validation;

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
    List<User> users = new ArrayList<>();

    static int ALLOWED_PASSWORD_ATTEMPTS = 3;

    static {
        gameState = GameState.IN_PROGRESS;
        scanner = new Scanner(System.in);
        hangmanBoot = new HangmanBoot();
        try {
            allowedMistake = GameProperties.getAllowedMistakes();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    public void menu() {
        int inputtedNumber = OutputManager.showMenu(scanner);
        users = UserData.loadUsers();
        switch (inputtedNumber) {
            case 1 -> singUp();
            case 2 -> logIn();
            case 3 -> OutputManager.showGameRules();
            case 4 -> showGameStats();
            case 0 -> exitGame();
        }
    }

    private void showGameStats() {
        OutputManager.showMessage("NickName: \t\tTotalScore", TEXT_BLUE);
        users.forEach(user -> OutputManager.showMessage(TEXT_GREEN+ user.getNickName() + "\t\t\t"+ TEXT_GREEN + user.getTotalScore()));
        OutputManager.hr();
        OutputManager.showMessage("any key to go back to Menu", TEXT_BLUE);
        InputManager.getString(scanner);
        menu();
    }

    private void exitGame() {
        UserData.saveUsers(users);
        System.exit(0);
    }

    private void logIn() {
        OutputManager.showMessage("Please provide your nickName");
        String nickName = InputManager.getWordString(scanner);
        if (users.stream().noneMatch(user -> user.getNickName().equals(nickName))) {
            OutputManager.showErrMessage("wrong nickName, there doesn't exist a user with that nickname");
            OutputManager.showMessage("""
                    1. Try again
                    2. SingUp
                    """);
            int ans = InputManager.getInt(scanner);
            switch (ans) {
                case 1 -> logIn();
                case 2 -> singUp();
            }
        }
        User user = users.stream().filter(user1 -> user1.getNickName().equals(nickName)).toList().get(0);
        OutputManager.showMessage("Please type you password:");
        String password = InputManager.getString(scanner);
        int attempts = 0;
        while (!Validation.isValidPassword(password, user)) {
            OutputManager.showErrMessage("Wrong password!");
            OutputManager.showMessage("Try again" + TEXT_RED + "Y/N");
            String ans = InputManager.getLetter(scanner);
            if (attempts >= ALLOWED_PASSWORD_ATTEMPTS) {
                OutputManager.showErrMessage("You have passed the allowed attempts, Going back to menu");
                menu();
            }
            if (!ans.equalsIgnoreCase("y"))
                menu();

            attempts++;
            password = InputManager.getString(scanner);
        }
        startGame(user);
    }

    private void singUp() {
        OutputManager.showMessage("Please provide a nickName");
        String nickName = InputManager.getWordString(scanner);
        while (userExist(users, nickName)) {
            OutputManager.showErrMessage("This nickname is taken. Please chose other one or: " + TEXT_BLUE + "0. to go back");
            nickName = InputManager.getWordString(scanner);
            if (nickName.equals("0")){
                menu();
                return;
            }
        }
        OutputManager.showMessage("Please provide a password");
        String password = InputManager.setPassword(scanner);
        User user = new User(nickName, password);
        users.add(user);

        startGame(user);
    }

    private boolean userExist(List<User> users, String nickname) {
        return users.stream().anyMatch(u -> u.getNickName().equals(nickname));
    }

    public void startGame(User user) {
        Map<String, String> words = LoadWords.loadData(Path.of("Hangman/src/main/resources/HangmanWords.txt"));
        Set<Character> usedLetters = new HashSet<>();
        OutputManager.hr();
        OutputManager.showMessage("\t\t\t" + user.getNickName() +" Welcome to Hang Man game!");
        String givenWord = hangmanBoot.getRandomWord(words);
//        givenWord = words.keySet().toArray(String[]::new)[1];
        String definition = words.get(givenWord);

        StringBuilder inputtedWord = new StringBuilder();
        inputtedWord.append(givenWord.replaceAll("\\w", "-"));

        while (gameState == GameState.IN_PROGRESS) {
            OutputManager.hr();
            OutputManager.showMessage("Please guess a letter: " + TEXT_BLUE +"0. back to menu");
            OutputManager.showMessage(TEXT_BLUE + "Hint: "
                    + TEXT_GREEN + definition);
            OutputManager.showMessage(inputtedWord + "", TEXT_BLUE);

            char letter = InputManager.getLetter(scanner).toLowerCase().charAt(0);
            if (letter == '0') {
                menu();
                return;
            }
            if (isUsedLetter(usedLetters, letter)) {
                OutputManager.showMessage("You already have used that letter", TEXT_BLUE);

            } else if (replaceLetters(givenWord, inputtedWord, letter)) {
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
            OutputManager.showMessage("used letters" + usedLetters);

            usedLetters.add(letter);

        }
        OutputManager.showMessage("Total score: " + TEXT_BLUE + user.getTotalScore());
        OutputManager.showMessage("Score: " + TEXT_BLUE + user.getScore());
        OutputManager.showMessage("Mistakes: " + TEXT_RED + MISTAKE);
        gameEnded();
    }

    private boolean isUsedLetter(Set<Character> usedLetters, char letter) {
        return usedLetters.contains(letter);
    }


    private void gameEnded() {
        OutputManager.showMessage(TEXT_BLUE + "Do you want to play an other game?"
                + TEXT_RED + " Y/N");
        String ans = InputManager.getLetter(scanner);
        if (ans.equals("y"))
            resetGame();
        else if(!ans.equals("n")){
            OutputManager.showErrMessage("wrong answer"  + TEXT_RED + " Y/N");
        }
    }

    private void resetGame() {
        gameState = GameState.IN_PROGRESS;
        MISTAKE = 0;
        startGame(User.currentUser);
        User.currentUser.setScore(0);
    }

    private boolean hasLose() {
        return ++MISTAKE > allowedMistake - 1;
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
