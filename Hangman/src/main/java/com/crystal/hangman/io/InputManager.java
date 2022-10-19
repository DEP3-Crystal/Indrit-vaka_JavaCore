package com.crystal.hangman.io;

import com.crystal.hangman.secirity.Validation;

import java.util.Scanner;
import java.util.function.Predicate;

public class InputManager {

    private static InputManager instance;
    private final Scanner scanner = new Scanner(System.in);
    private final OutputManager outputManager = OutputManager.getInstance();
    private final Validation validation = Validation.getInstance();

    public static synchronized InputManager getInstance() {
        if (instance == null)
            instance = new InputManager();
        return instance;
    }

    private InputManager() {

    }

    /**
     * @return a single letter no special characters allowed
     */
    public String getLetter() {
        return validAnswer(validation::isValidLetter, "please give only one letter and letter should be [A-z]");
    }

    /**
     * This method returns a string without special characters, its may be used for nickName
     *
     * @return string without any special character
     */
    public String getWordString() {
        return validAnswer(validation::isValidNickName, "your name shouldn't contain non words character ($#?/\\...)");
    }


    /**
     * Gets input from user and makes sure that's a number
     *
     * @return an int
     */
    public int getInt() {

        return Integer.parseInt(validAnswer(validation::isValidNumber, "Please give a valid number"));
    }


    /**
     * Validates and returns password as string
     *
     * @return the validated password
     */
    public String getPassword() {

        return validAnswer(validation::isValidPassword, """
                You password should contain:
                   At least one uppercase English letter
                   At least one lowercase English letter
                   At least one digit
                   At least 8 digits
                   and At least one special character""");
    }

    /**
     * @return a string without making any validations
     */
    public String getString() {
        return scanner.nextLine();
    }


    private String validAnswer(Predicate<String> validator, String message) {
        String input;
        do {
            input = scanner.nextLine();

            if (!validator.test(input)) {
                outputManager.showErrMessage(message);
            }

        } while (!validator.test(input));
        return input;
    }
}