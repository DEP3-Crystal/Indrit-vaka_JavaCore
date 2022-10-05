package com.crystal.atm.view;

import com.crystal.atm.security.Validator;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Predicate;

public class InputManagerCli implements InputManager {
    //TODO remove repeated code
    private final Scanner scanner = new Scanner(System.in);
    private final OutputManager outputManager;
    private final Validator validator = new Validator();

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
        return validAnswer(validator::isValidEmail, "please give a valid email");
    }

    /**
     * @return a single letter no special characters allowed
     */
    public String getLetter() {

        return validAnswer(validator::isValidLetter, "your name shouldn't contain non words character ($#/\\...)");

    }

    /**
     * This method returns a string without special characters, its may be used for nickName
     *
     * @return string without any special character
     */
    public String getWordLettersOnly() {
        return validAnswer(validator::containsOnlyLetters, "your name shouldn't contain non words character ($#/\\...)");
    }


    /**
     * @return return a LocalDate obj created from CLI
     */
    public LocalDate getDate() {
        int year, month, dayOfMonth;

        do {
            outputManager.showLabel("\tyear: ");
            year = getInt();
            if (validator.isNotValidYear(year))
                outputManager.showErrMessage("Invalid year");
        } while (validator.isNotValidYear(year));
        do {

            outputManager.showLabel("\tmonth: ");
            month = getInt();
            if (validator.isNotValidMonth(month)) {
                outputManager.showErrMessage("Invalid Month");
            }

        } while (validator.isNotValidMonth(month));
        do {

            outputManager.showLabel("\tdate: ");
            dayOfMonth = getInt();

            if (validator.isNotValidDay(year, month, dayOfMonth)) {
                outputManager.showErrMessage("Invalid day");
            }
        } while (validator.isNotValidDay(year, month, dayOfMonth));
        return LocalDate.of(year, month, dayOfMonth);
    }


    /**
     * Gets input from user and makes sure that's a number
     *
     * @return an int
     */
    public int getInt() {
        return Integer.parseInt(validAnswer(validator::isValidNumber, "Please give a valid number"));
    }

    public String getNumbersOnly() {
        return validAnswer(validator::isValidNumber, "Please give a valid number");
    }

    @Override
    public long getLong() {
        return Long.parseLong(validAnswer(validator::isValidNumber, "Please give a valid number"));
    }

    public String validAnswer(Predicate<String> validator, String message) {
        String input;

        do {
            input = scanner.nextLine();

            if (!validator.test(input))
                outputManager.showErrMessage(message);

        } while (!validator.test(input));
        return input;
    }
}