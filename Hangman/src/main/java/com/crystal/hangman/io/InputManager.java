package com.crystal.hangman.io;

import com.crystal.hangman.secirity.Validation;

import java.util.Scanner;

public class InputManager {

    public static String getLetter(Scanner scanner) {
        String input;
        boolean valid;
        do {
            input = scanner.nextLine();
            valid = Validation.isValidLetter(input);
            if (input.length() > 1) {
                OutputManager.showErrMessage("you should give only one letter");
            }
            if (!valid) {
                OutputManager.showErrMessage("please give a valid letter [A-Z] or [a-z]");
            }

        } while (!valid || input.length() > 1);
        return input;
    }

    public static String getWordString(Scanner scanner) {
        String input;
        boolean valid;
        do {
            input = scanner.nextLine();
            valid = Validation.isValidNickName(input);

            if (!valid) {
                OutputManager.showErrMessage("your name shouldn't contain non words character ($#/\\...)");
            }
        } while (!valid);
        return input;
    }


    public static int getInt(Scanner scanner) {
        String input;
        int number = -50;
        do {
            input = scanner.nextLine();

            if (!Validation.isValidNumber(input))
                OutputManager.showErrMessage("Please give a valid number");
            else {
                number = Integer.parseInt(input);
            }
        } while (!Validation.isValidNumber(input));
        return number;
    }

}
