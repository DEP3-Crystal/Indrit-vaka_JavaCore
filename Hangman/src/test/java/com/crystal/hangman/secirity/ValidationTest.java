package com.crystal.hangman.secirity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationTest {

    @Test
    void isValidNumber() {
        //Number and wordLetters
        Assertions.assertFalse(Validation.isValidNumber("5a"));
        //Word and nonWord letters
        Assertions.assertFalse(Validation.isValidNumber("asc."));
        // Ending with number
        Assertions.assertFalse(Validation.isValidNumber("a2"));
        //Number in the middle
        Assertions.assertFalse(Validation.isValidNumber(".a5sc"));
        //Valid number
        Assertions.assertTrue(Validation.isValidNumber("0152"));
        //Negative numbers
        Assertions.assertTrue(Validation.isValidNumber("-224"));
    }

    @Test
    void isValidLetter() {
        //not numbers
        Assertions.assertTrue(Validation.isValidLetter("a"));
        //length more than one
        Assertions.assertFalse(Validation.isValidLetter("1a"));
        // non Word letters
        Assertions.assertFalse(Validation.isValidLetter("."));

    }

    @Test
    void isValidNickName() {
        //containing numbers
        Assertions.assertTrue(Validation.isValidNickName("1a"));
        //word and non word letters
        Assertions.assertFalse(Validation.isValidNickName("a."));
        //Starting with non word letter
        Assertions.assertFalse(Validation.isValidNickName("?ad"));
        // Only word letters
        Assertions.assertTrue(Validation.isValidNickName("aaxaz"));

    }

    @Test
    void isValidPassword() {
        // length less than 8 letters
        Assertions.assertFalse(Validation.isValidPassword("Te12.!"));
        // Only Letters all same case
        Assertions.assertFalse(Validation.isValidPassword("ACASCECASCS"));

        // Only letters different case
        Assertions.assertFalse(Validation.isValidPassword("AcascAASC"));

        // letters and numbers
        Assertions.assertFalse(Validation.isValidPassword("Anmmes1224"));
        // letters, numbers, nonWord letters
        Assertions.assertTrue(Validation.isValidPassword("Test1234."));
    }

}