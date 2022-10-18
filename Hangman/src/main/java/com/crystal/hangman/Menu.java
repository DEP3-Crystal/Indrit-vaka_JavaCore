package com.crystal.hangman;

import com.crystal.hangman.ai.HangmanBoot;
import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.dao.word.WordDataAccess;
import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.model.User;
import com.crystal.hangman.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static com.crystal.hangman.io.ConsoleColors.*;

public class Menu {

    private final UserService userService;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final UserDataAccess userDataAccess;

   private final HangmanBoot hangmanBoot;



    public Menu(UserService userService, InputManager inputManager, OutputManager outputManager, UserDataAccess userDataAccess, WordDataAccess wordDataAccess) {
        this.userService = userService;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.userDataAccess = userDataAccess;
        hangmanBoot = new HangmanBoot(wordDataAccess, userDataAccess);
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
        users.forEach(user -> outputManager.showMessage(TEXT_GREEN + user.getNickName() + "\t\t\t" + TEXT_GREEN + user.getHighScore()));
        outputManager.hr();
        outputManager.showMessage("any key to go back to Menu", TEXT_BLUE);
        inputManager.getString();
        menu();
    }

    private void exitGame() {
        UserData.saveUsers(users);
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

        User user = userDataAccess.getUserByNickName(nickName).orElseThrow(); // we know for sure that user exist
        outputManager.showMessage("Please type you password:");
        if(isValidPassword(user)) {
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
        users.add(user);

        startTheGame(user);
    }

    private void startTheGame(User user){
        hangmanBoot.startTheGame(user);

    }

}
