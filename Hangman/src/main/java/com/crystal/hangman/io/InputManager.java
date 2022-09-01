package com.crystal.hangman.io;

import com.crystal.hangman.secirity.Validation;

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
            valid = Validation.isValidLetter(input);
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
            valid = Validation.isValidNickName(input);

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

            if (!Validation.isValidNumber(input))
                OutputManager.showErrMessage("Please give a valid number");
            else {
                number = Integer.parseInt(input);
            }
        } while (!Validation.isValidNumber(input));
        return number;
    }


    /**
     * Validates and returns password as string
     * @param scanner scanner obj
     * @return the validated password
     * */
    public static String setPassword(Scanner scanner) {
        String password;
        do {
            password = scanner.nextLine();
            if (!Validation.isValidPassword(password)) {
                OutputManager.showErrMessage("""
                        You password should contain:
                            At least one uppercase English letter
                            At least one lowercase English letter
                            At least one digit
                            At least 8 digits
                            and At least one special character
                        """);
            }
        } while (!Validation.isValidPassword(password));
        return  password;
    }

    /**
     * @return a string without making any validations
     */
    public static String getString(Scanner scanner) {
        return scanner.nextLine();
    }
}