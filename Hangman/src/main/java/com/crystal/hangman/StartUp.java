package com.crystal.hangman;

import com.crystal.hangman.ai.HangmanBoot;
import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.dao.user.UserDataAccessFromMemory;
import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.service.UserService;

import java.util.Scanner;

public class StartUp {
    public static void main(String[] args) {
        UserDataAccess userDataAccess = new UserDataAccessFromMemory();
        UserService userService = new UserService(userDataAccess);
        OutputManager outputManager = new OutputManager();
        InputManager inputManager = new InputManager(new Scanner(System.in),outputManager);
        HangmanBoot hangmanBoot = new HangmanBoot(userService, inputManager, outputManager, userDataAccess);
        hangmanBoot.menu();
    }
}
