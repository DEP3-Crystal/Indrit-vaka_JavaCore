package com.crystal.atm.view;

import java.time.LocalDate;

public interface InputManager {

    /**
     * @return string text without any validation
     */
    String getString();

    /**
     * @return string text without any validation
     */
    String getEmail();

    /**
     * @return a single letter no special characters allowed
     */
    String getLetter();

    /**
     * This method returns a string without special characters, its may be used for nickName
     *
     * @return string without any special character
     */
    String getWordLettersOnly();


    /**
     * @return return a LocalDate obj created from CLI
     */
    LocalDate getDate();

    /**
     * Gets input from user and makes sure that's a number
     *
     * @return an int
     */
    int getInt();

    String getNumbersOnly();

    long getLong();
}
