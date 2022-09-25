package com.crystal.atm.io;

import com.crystal.atm.security.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class InputManager {


    /**
     * @param scanner scanner obj
     * @return string text without any validation
     */
    public static String getString(Scanner scanner) {
        return scanner.nextLine();
    }
    /**
     * @param scanner scanner obj
     * @return string text without any validation
     */
    public static String getEmail(Scanner scanner) {
        String input;
        boolean valid;
        do {
            input = scanner.nextLine();
            valid = Validator.isValidEmail(input);

            if (!valid) {
                OutputManager.showErrMessage("please give a valid email");
            }

        } while (!valid);
        return input.toLowerCase();
    }

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
    public static String getWordLettersOnly(Scanner scanner) {
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
     * @param scanner scanner obj
     * @return return
     */
    public static LocalDate getDate(Scanner scanner) {
            OutputManager.showLabel("year: ");
            int year = getInt(scanner);
            OutputManager.showLabel("month: ");
            int month = getInt(scanner);
            OutputManager.showLabel("date: ");
            int dayOfMonth = getInt(scanner);
            return LocalDate.of(year,month,dayOfMonth);
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
    public static String getNumbersOnly(Scanner scanner) {
        String input;
        String number ="";
        do {
            input = scanner.nextLine();

            if (!Validator.isValidNumber(input))
                OutputManager.showErrMessage("Please give a valid number");
            else {
                number = input;
            }
        } while (!Validator.isValidNumber(input));
        return number;
    }
}