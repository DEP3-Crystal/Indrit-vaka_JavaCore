package com.crystal.hangman;

import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.dao.user.UserDataAccessFromProperties;
import com.crystal.hangman.dao.word.WordDataAccess;
import com.crystal.hangman.dao.word.WordDataAccessFromFile;
import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.logger.Logger;
import com.crystal.hangman.service.GameService;
import com.crystal.hangman.service.UserService;

public class StartUp {
    public static void main(String[] args) {


        UserDataAccess userDataAccess = new UserDataAccessFromProperties();
        WordDataAccess wordDataAccess = new WordDataAccessFromFile();

        UserService userService = UserService.getInstance(userDataAccess);
        OutputManager outputManager = OutputManager.getInstance();
        InputManager inputManager = InputManager.getInstance();
        GameService gameService = new GameService(wordDataAccess, userDataAccess);
        Menu menu = new Menu(userService, inputManager, outputManager, gameService);

        try {
            menu.menu();
        } catch (Exception ex) {
            Logger.logException(ex);
            menu.menu();
        }
    }
}
