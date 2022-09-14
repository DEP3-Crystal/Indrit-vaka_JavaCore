package com.crystal.ramdom_person.io;

import com.crystal.ramdom_person.validator.Validator;

import java.util.Scanner;

public class InputManager {

    /**
     * @param scanner scanner obj
     * @return a single letter no special characters allowed
     */
    public static String getLetter(Scanner scanner) {
        String input;
        boolean valid;
        do {
            input = scanner.nextLine();
            valid = Validator.isValidLetter(input);
            if (input.length() > 1) {
                OutputManager.showErrMessage("you should give only one letter");
            }
            if (!valid) {
                OutputManager.showErrMessage("please give a valid letter [A-Z] or [a-z]");
            }

        } while (!valid || input.length() > 1);
        return input.toLowerCase();
    }

    /**
     * This method returns a string without special characters, its may be used for nickName
     *
     * @param scanner scanner obj
     * @return string without any special character
     */
    public static String getWordString(Scanner scanner) {
        String input;
        boolean valid;
        do {
            input = scanner.nextLine();
            valid = Validator.containsOnlyLetters(input);

            if (!valid) {
                OutputManager.showErrMessage("your name shouldn't contain non words character ($#/\\...)");
            }
        } while (!valid);
        return input;
    }
    /**
     * Gets input from user and makes sure that's a number
     *
     * @param scanner scanner obj
     * @return an int
     */
    public static int getInt(Scanner scanner) {
        String input;
        int number = -50;
        do {
            input = scanner.nextLine();

            if (!Validator.isValidNumber(input))
                OutputManager.showErrMessage("Please give a valid number");
            else {
                number = Integer.parseInt(input);
            }
        } while (!Validator.isValidNumber(input));
        return number;
    }

}
