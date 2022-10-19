package com.crystal.hangman.secirity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationTest {
    Validation validation = Validation.getInstance();

    @Test
    void isValidNumber() {
        //Number and wordLetters
        Assertions.assertFalse(validation.isValidNumber("5a"));
        //Word and nonWord letters
        Assertions.assertFalse(validation.isValidNumber("asc."));
        // Ending with number
        Assertions.assertFalse(validation.isValidNumber("a2"));
        //Number in the middle
        Assertions.assertFalse(validation.isValidNumber(".a5sc"));
        //Valid number
        Assertions.assertTrue(validation.isValidNumber("0152"));
        //Negative numbers
        Assertions.assertTrue(validation.isValidNumber("-224"));
    }

    @Test
    void isValidLetter() {
        //not numbers
        Assertions.assertTrue(validation.isValidLetter("a"));
        //length more than one
        Assertions.assertFalse(validation.isValidLetter("1a"));
        // non Word letters
        Assertions.assertFalse(validation.isValidLetter("."));
        // number
        Assertions.assertTrue(validation.isValidLetter("1"));
    }

    @Test
    void isValidNickName() {
        //containing numbers
        Assertions.assertTrue(validation.isValidNickName("1a"));
        //word and non word letters
        Assertions.assertFalse(validation.isValidNickName("a."));
        //Starting with non word letter
        Assertions.assertFalse(validation.isValidNickName("?ad"));
        // Only word letters
        Assertions.assertTrue(validation.isValidNickName("aaxaz"));

    }

    @Test
    void isValidPassword() {
        // length less than 8 letters
        Assertions.assertFalse(validation.isValidPassword("Te12.!"));
        // Only Letters all same case
        Assertions.assertFalse(validation.isValidPassword("ACASCECASCS"));

        // Only letters different case
        Assertions.assertFalse(validation.isValidPassword("AcascAASC"));

        // letters and numbers
        Assertions.assertFalse(validation.isValidPassword("Anmmes1224"));
        // letters, numbers, nonWord letters
        Assertions.assertTrue(validation.isValidPassword("Test1234."));
    }

}