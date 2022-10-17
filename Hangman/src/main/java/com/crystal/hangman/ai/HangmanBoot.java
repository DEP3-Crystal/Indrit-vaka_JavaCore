package com.crystal.hangman.ai;

import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.dao.GameProperties;
import com.crystal.hangman.dao.LoadWords;
import com.crystal.hangman.dao.user.UserData;
import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.model.GameState;
import com.crystal.hangman.model.User;
import com.crystal.hangman.service.UserService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

import static com.crystal.hangman.io.ConsoleColors.*;

public class HangmanBoot {

    private static int ALLOWED_PASSWORD_ATTEMPTS = 3;
    private final UserService userService;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final UserDataAccess userDataAccess;
    private List<User> users = new ArrayList<>();
    private int mistake = 0;
    private int allowedMistake;
    private GameState gameState;

    {
        gameState = GameState.IN_PROGRESS;
        try {
            allowedMistake = GameProperties.getAllowedMistakes();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public HangmanBoot(UserService userService, InputManager inputManager, OutputManager outputManager, UserDataAccess userDataAccess) {
        this.userService = userService;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.userDataAccess = userDataAccess;
    }


    public void menu() {
        int inputtedNumber = showMenu();
        users = UserData.loadUsers();
        switch (inputtedNumber) {
            case 1 -> singUp();
            case 2 -> logIn();
            case 3 -> outputManager.showGameRules();
            case 4 -> showGameStats();
            case 0 -> exitGame();
        }
    }

    public int showMenu() {
        outputManager.hr();
        outputManager.showMessage("""
                Welcome to Hangman Game!
                Please press:
                    1. to start a new the game (Sing-up)
                    2. to Login (Load previous game)
                    3. to view the game rules
                    4. game stats
                    0. to Exit the game
                """);
        outputManager.hr();
        return inputManager.getInt();
    }

    private void showGameStats() {
        outputManager.showMessage("NickName: \t\tTotalScore", TEXT_BLUE);
        users.forEach(user -> outputManager.showMessage(TEXT_GREEN + user.getNickName() + "\t\t\t" + TEXT_GREEN + user.getTotalScore()));
        outputManager.hr();
        outputManager.showMessage("any key to go back to Menu", TEXT_BLUE);
        inputManager.getString();
        menu();
    }

    private void exitGame() {
        UserData.saveUsers(users);
        System.exit(0);
    }

    private void logInOld() {
        outputManager.showMessage("Please provide your nickName");
        String nickName = inputManager.getWordString();
        if (users.stream().noneMatch(user -> user.getNickName().equals(nickName))) {
            outputManager.showErrMessage("wrong nickName, there doesn't exist a user with that nickname");
            outputManager.showMessage("""
                    1. Try again
                    2. SingUp
                    """);
            int ans = inputManager.getInt();
            switch (ans) {
                case 1 -> logIn();
                case 2 -> singUp();
            }
        }

        User user = users.stream().filter(user1 -> user1.getNickName().equals(nickName)).toList().get(0);
        outputManager.showMessage("Please type you password:");
        String password = inputManager.getString();
        int attempts = 0;
        while (userService.doesPasswordMatches(password, user)) {
            outputManager.showErrMessage("Wrong password!");
            outputManager.showMessage("Try again" + TEXT_RED + "Y/N");
            String ans = inputManager.getLetter();
            if (attempts >= ALLOWED_PASSWORD_ATTEMPTS) {
                outputManager.showErrMessage("You have passed the allowed attempts, Going back to menu");
                menu();
            }
            if (!ans.equalsIgnoreCase("y"))
                menu();

            attempts++;
            password = inputManager.getString();
        }
        startGame(user);
    }

    private void logIn() {
        outputManager.showMessage("Please provide your nickName");
        String nickName = inputManager.getWordString();

        if (!userService.doesUserExist(nickName)) {
            outputManager.showErrMessage("wrong nickName, there doesn't exist a user with that nickname");
            outputManager.showMessage("""
                    1. Try again
                    2. SingUp
                    """);
            int ans = inputManager.getInt();
            switch (ans) {
                case 1 -> logIn();
                case 2 -> singUp();
            }
        }

        // TODO question should i use the DAO or a service object to get the user
        User user = userDataAccess.getUserByNickName(nickName).orElseThrow(); // we know for sure that user exist
        outputManager.showMessage("Please type you password:");
        String password = inputManager.getString();
        int attempts = 0;
        while (!userService.doesPasswordMatches(password, user)) {
            outputManager.showErrMessage("Wrong password!");
            outputManager.showMessage("Try again" + TEXT_RED + "Y/N");
            String ans = inputManager.getLetter();
    //TODO implement !userService.hasLeftPasswordAttempts()
            //if(!userService.hasLeftPasswordAttempts()){
            if(attempts >=attempts){
                outputManager.showErrMessage("You have passed the allowed attempts, Going back to menu");
                menu();
                return;
            }
            if (!ans.equalsIgnoreCase("y"))
                menu();

            attempts++;
            password = inputManager.getString();
        }
        startGame(user);
    }

    private void singUp() {
        outputManager.showMessage("Please provide a nickName");
        String nickName = inputManager.getWordString();
        while (userService.doesUserExist(nickName)) {
            outputManager.showErrMessage("This nickname is taken. Please chose other one or: " + TEXT_BLUE + "0. to go back");
            nickName = inputManager.getWordString();
            if (nickName.equals("0")) {
                menu();
                return;
            }
        }
        outputManager.showMessage("Please provide a password");
        String password = inputManager.getPassword();
        User user = userService.createUser(nickName, password);
        users.add(user);

        startGame(user);
    }


    public void startGame(User user) {

        Map<String, String> words = LoadWords.loadData(Path.of("Hangman/src/main/resources/HangmanWords.txt"));
        Set<Character> usedLetters = new HashSet<>();
        outputManager.hr();
        outputManager.showMessage("\t\t\t" + user.getNickName() + " Welcome to Hang Man game!");
        String givenWord = getRandomWord(words);
//        givenWord = words.keySet().toArray(String[]::new)[1];
        String definition = words.get(givenWord);

        StringBuilder inputtedWord = new StringBuilder();
        inputtedWord.append(givenWord.replaceAll("\\w", "-"));

        while (gameState == GameState.IN_PROGRESS) {
            outputManager.hr();
            outputManager.showMessage("Please guess a letter: " + TEXT_BLUE + "0. back to menu");
            outputManager.showMessage(TEXT_BLUE + "Hint: "
                    + TEXT_GREEN + definition);
            outputManager.showMessage(inputtedWord + "", TEXT_BLUE);

            char letter = inputManager.getLetter().toLowerCase().charAt(0);
            if (letter == '0') {
                menu();
                return;
            }
            if (isUsedLetter(usedLetters, letter)) {
                outputManager.showMessage("You already have used that letter", TEXT_BLUE);

            } else if (replaceLetters(givenWord, inputtedWord, letter)) {
                user.incrementScore(1);
                boolean won = hasWon(givenWord, inputtedWord);
                if (won) {
                    gameState = GameState.WON;
                    outputManager.winMessage();
                    break;
                }
            } else {
                outputManager.showErrMessage("The given letter isn't in the word");
                if (hasLose()) {
                    gameState = GameState.LOSE;
                    outputManager.loseMessage();
                    break;
                }
                outputManager.showMessage("You have " + TEXT_RED + (allowedMistake - mistake) + TEXT_RESET + " chances");

            }
            outputManager.showMessage("used letters" + usedLetters);

            usedLetters.add(letter);

        }
        outputManager.showMessage("Total score: " + TEXT_BLUE + user.getTotalScore());
        outputManager.showMessage("Score: " + TEXT_BLUE + user.getScore());
        outputManager.showMessage("Mistakes: " + TEXT_RED + mistake);
        gameEnded();
    }

    private boolean isUsedLetter(Set<Character> usedLetters, char letter) {
        return usedLetters.contains(letter);
    }


    private void gameEnded() {
        outputManager.showMessage(TEXT_BLUE + "Do you want to play an other game?"
                + TEXT_RED + " Y/N");
        String ans = inputManager.getLetter();
        if (ans.equals("y"))
            resetGame();
        else if (!ans.equals("n")) {
            outputManager.showErrMessage("wrong answer" + TEXT_RED + " Y/N");
        } else {
            UserData.saveUsers(users);
        }
    }

    private void resetGame() {
        gameState = GameState.IN_PROGRESS;
        mistake = 0;
        startGame(User.getCurrentUser());
        User.getCurrentUser().setScore(0);
    }

    private boolean hasLose() {
        return ++mistake >= allowedMistake;
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
