package com.crystal.hangman;

import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.model.GameData;
import com.crystal.hangman.model.GameState;
import com.crystal.hangman.model.User;
import com.crystal.hangman.service.GameService;
import com.crystal.hangman.service.UserService;

import java.util.Map;

import static com.crystal.hangman.io.ConsoleColors.*;

public class Menu {

    private final UserService userService;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final GameService gameService;
    private final GameData gameData = new GameData();


    public Menu(UserService userService, InputManager inputManager, OutputManager outputManager, GameService gameService) {
        this.userService = userService;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.gameService = gameService;
    }


    public void menu() {

        int inputtedNumber = showMenu();
        switch (inputtedNumber) {
            case 1 -> singUp();
            case 2 -> logIn();
            case 3 -> outputManager.showGameRules();
            case 4 -> showGameStats();
            case 0 -> exitGame();
            default -> {
                outputManager.showErrMessage("Not valid option");
                menu();
            }
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
        Map<String, User> users = userService.getAllUsers();
        printUsers(users);
        outputManager.hr();
        outputManager.showMessage("any key to go back to Menu", TEXT_BLUE);
        inputManager.getString();
        menu();
    }

    private void printUsers(Map<String, User> users) {
        users.values().forEach(user -> outputManager.showMessage(TEXT_GREEN + user.getNickName() + "\t\t\t" + TEXT_GREEN + user.getHighScore()));
    }

    private void exitGame() {
        System.exit(0);
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
                case 1 -> {
                    logIn();
                    return;
                }
                case 2 -> singUp();
                default -> {
                    outputManager.showErrMessage("Not valid option");
                    logIn();
                    return;
                }
            }
        }

        User user = userService.getUserByNickName(nickName).orElseThrow(); // we know for sure that user exist
        outputManager.showMessage("Please type you password:");
        if (isValidPassword(user)) {
            startTheGame(user);
        }
    }

    private boolean isValidPassword(User user) {
        String password = inputManager.getString();
        while (!userService.doesPasswordMatches(password, user)) {
            outputManager.showErrMessage("Wrong password!");
            outputManager.showMessage("Try again" + TEXT_RED + "Y/N");
            String ans = inputManager.getLetter();

            if (!ans.equalsIgnoreCase("y")) {
                menu();
                return false;
            }
            password = inputManager.getString();
        }
        return true;
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
        userService.addUser(user);
        userService.saveUserData(user);
        startTheGame(user);
    }


    public void startTheGame(User user) {
        startTheGame(user, true);
    }

    public void startTheGame(User user, boolean firstRun) {

        if (firstRun) {
            outputManager.hr();
            outputManager.showMessage("\t\t\t" + user.getNickName() + " Welcome to Hang Man game!");
        }

        playGame(gameData);
        showGameResults(user, gameData);
        gameEnded(user, gameData);
    }

    private void playGame(GameData gameData) {
        String givenWord = gameService.getNextLevel();
        String definition = gameService.getDefinition(givenWord);
        StringBuilder dashedWord = gameService.getDashedWord(givenWord);

        while (gameData.getGameState() == GameState.IN_PROGRESS) {
            printWord(definition, dashedWord);

            char answer = inputManager.getLetter().toLowerCase().charAt(0);
            if (answer == '0') {
                menu();
            } else if (givenWord.contains(answer + "")) {
                dashedWord = gameService.replaceLetters(givenWord, dashedWord, answer);
                gameData.incrementScore();

                if (gameService.hasWon(givenWord, dashedWord)) {
                    outputManager.winMessage();
                    gameData.won();
                }

            } else {
                outputManager.showErrMessage("The given letter isn't in the word");
                gameData.wrongAnswer();
                if (gameService.hasLose(gameData)) {
                    gameData.lose();
                    outputManager.loseMessage();
                } else {
                    outputManager.showMessage("You have " + TEXT_RED + gameData.getLeftChances() + TEXT_RESET + " chances");
                }
            }
            if (gameService.isUsedLetter(gameData, answer)) {
                outputManager.showMessage("You already have used that letter", TEXT_BLUE);
                outputManager.showMessage("used letters" + gameData.getUsedLetters());

            }
            gameService.addUsedLetter(gameData, answer);
        }
    }

    private void printWord(String definition, StringBuilder inputtedWord) {
        outputManager.hr();
        outputManager.showMessage("Please guess a letter: " + TEXT_BLUE + "0. back to menu");
        outputManager.showMessage(TEXT_BLUE + "Hint: " + TEXT_GREEN + definition);

        outputManager.showMessage(inputtedWord + "", TEXT_BLUE);
    }

    private void showGameResults(User user, GameData gameData) {
        gameService.updateUserData(user, gameData);
        outputManager.showMessage("High score: " + TEXT_BLUE + user.getHighScore());
        outputManager.showMessage("Score: " + TEXT_BLUE + gameData.getScore());
        outputManager.showMessage("Mistakes: " + TEXT_RED + gameData.getMistakes());
    }


    private void gameEnded(User user, GameData gameData) {
        outputManager.showMessage(TEXT_BLUE + "Do you want to play an other game?"
                + TEXT_RED + " Y/N");
        String ans = inputManager.getLetter();
        if (ans.equalsIgnoreCase("y"))
            resetTheGame(user, gameData);
        else if (!ans.equals("n")) {
            outputManager.showErrMessage("wrong answer" + TEXT_RED + " Y/N");
        } else {
            outputManager.showMessage(TEXT_BLUE + "See you! :)");
        }
    }

    private void resetTheGame(User user, GameData gameData) {
        gameData.resetData();
        startTheGame(user);
    }

}
