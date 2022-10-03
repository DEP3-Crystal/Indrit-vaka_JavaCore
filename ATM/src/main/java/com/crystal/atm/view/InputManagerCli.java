package com.crystal.atm.view;

import com.crystal.atm.security.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class InputManagerCli implements InputManager {

    private final Scanner scanner = new Scanner(System.in);
    private final OutputManager outputManager;

    public InputManagerCli(OutputManager outputManager) {
        this.outputManager = outputManager;
    }

    /**
     * @return string text without any validation
     */
    public String getString() {
        return scanner.nextLine();
    }

    /**
     * @return string text without any validation
     */
    public String getEmail() {
        String input;
        boolean valid;
        do {
            input = scanner.nextLine();
            valid = Validator.isValidEmail(input);

            if (!valid) {
                outputManager.showErrMessage("please give a valid email");
            }

        } while (!valid);
        return input.toLowerCase();
    }

    /**
     * @return a single letter no special characters allowed
     */
    public String getLetter() {
        String input;
        boolean valid;
        do {
            input = scanner.nextLine();
            valid = Validator.isValidLetter(input);
            if (input.length() > 1) {
                outputManager.showErrMessage("you should give only one letter");
            }
            if (!valid) {
                outputManager.showErrMessage("please give a valid letter [A-Z] or [a-z]");
            }

        } while (!valid || input.length() > 1);
        return input.toLowerCase();
    }

    /**
     * This method returns a string without special characters, its may be used for nickName
     *
     * @return string without any special character
     */
    public String getWordLettersOnly() {
        String input;
        boolean valid;
        do {
            input = scanner.nextLine();
            valid = Validator.containsOnlyLetters(input);

            if (!valid) {
                outputManager.showErrMessage("your name shouldn't contain non words character ($#/\\...)");
            }
        } while (!valid);
        return input;
    }


    /**
     * @return return a LocalDate obj created from CLI
     */
    public LocalDate getDate() {
        int year, month, dayOfMonth;

        do {
            outputManager.showLabel("\tyear: ");
            year = getInt();
            if (Validator.isNotValidYear(year))
                outputManager.showErrMessage("Invalid year");
        } while (Validator.isNotValidYear(year));
        do {

            outputManager.showLabel("\tmonth: ");
            month = getInt();
            if (Validator.isNotValidMonth(month)) {
                outputManager.showErrMessage("Invalid Month");
            }

        } while (Validator.isNotValidMonth(month));
        do {

            outputManager.showLabel("\tdate: ");
            dayOfMonth = getInt();

            if (Validator.isNotValidDay(year, month, dayOfMonth)) {
                outputManager.showErrMessage("Invalid day");
            }
        } while (Validator.isNotValidDay(year, month, dayOfMonth));
        return LocalDate.of(year, month, dayOfMonth);
    }


    /**
     * Gets input from user and makes sure that's a number
     *
     * @return an int
     */
    public int getInt() {
        String input;
        int number = -50;
        do {
            input = scanner.nextLine();

            if (!Validator.isValidNumber(input))
                outputManager.showErrMessage("Please give a valid number");
            else {
                number = Integer.parseInt(input);
            }
        } while (!Validator.isValidNumber(input));
        return number;
    }

    public String getNumbersOnly() {
        String input;
        String number = "";
        do {
            input = scanner.nextLine();

            if (!Validator.isValidNumber(input))
                outputManager.showErrMessage("Please give a valid number");
            else {
                number = input;
            }
        } while (!Validator.isValidNumber(input));
        return number;
    }

    @Override
    public long getLong() {
        String input;
        long number = -50;
        do {
            input = scanner.nextLine();

            if (!Validator.isValidNumber(input))
                outputManager.showErrMessage("Please give a valid number");
            else {
                number = Integer.parseInt(input);
            }
        } while (!Validator.isValidNumber(input));
        return number;
    }
}