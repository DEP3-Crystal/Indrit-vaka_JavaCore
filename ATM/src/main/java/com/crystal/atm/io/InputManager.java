package com.crystal.atm.io;

import com.crystal.atm.security.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class InputManager {

    static Scanner scanner = new Scanner(System.in);
    /**
     * @return string text without any validation
     */
    public static String getString() {
        return scanner.nextLine();
    }

    /**
     * @return string text without any validation
     */
    public static String getEmail() {
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
     * @return a single letter no special characters allowed
     */
    public static String getLetter() {
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
     * @return string without any special character
     */
    public static String getWordLettersOnly() {
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
     * @return return a LocalDate obj created from CLI
     */
    public static LocalDate getDate() {
        int year, month, dayOfMonth;

        do {
            OutputManager.showLabel("year: ");
            year = getInt();
            if (Validator.isNotValidYear(year))
                OutputManager.showErrMessage("Invalid year");
        } while (Validator.isNotValidYear(year));
        do {

            OutputManager.showLabel("month: ");
            month = getInt();
            if (Validator.isNotValidMonth(month)) {
                OutputManager.showErrMessage("Invalid Month");
            }

        } while (Validator.isNotValidMonth(month));
        do {

            OutputManager.showLabel("date: ");
            dayOfMonth = getInt();

            if (Validator.isNotValidDay(year, month, dayOfMonth)) {
                OutputManager.showErrMessage("Invalid day");
            }
        } while (Validator.isNotValidDay(year, month, dayOfMonth));
        return LocalDate.of(year, month, dayOfMonth);
    }


    /**
     * Gets input from user and makes sure that's a number
     *
     * @return an int
     */
    public static int getInt() {
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

    public static String getNumbersOnly() {
        String input;
        String number = "";
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