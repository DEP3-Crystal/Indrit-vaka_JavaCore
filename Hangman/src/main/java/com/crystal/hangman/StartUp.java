package com.crystal.hangman;

import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.dao.user.UserDataAccessFromMemory;
import com.crystal.hangman.dao.word.WordDataAccess;
import com.crystal.hangman.dao.word.WordDataAccessFromFile;
import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.service.UserService;

public class StartUp {
    public static void main(String[] args) {
        UserDataAccess userDataAccess = new UserDataAccessFromMemory();
        UserService userService = UserService.getInstance(userDataAccess);
        OutputManager outputManager = OutputManager.getInstance();
        InputManager inputManager = InputManager.getInstance();
        WordDataAccess wordDataAccess  = new WordDataAccessFromFile();
        Menu menu = new Menu(userService, inputManager, outputManager, userDataAccess, wordDataAccess);
        menu.menu();
    }
}
