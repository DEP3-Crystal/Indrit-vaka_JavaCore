package com.crystal.ramdom_person.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    void checkValidEmail() {
        Assertions.assertTrue(Validator.validEmail("tw@asd.com"));
        Assertions.assertTrue(Validator.validEmail("t12@asd.com"));
    }

    @Test
    public void checkInValidEmail() {
        Assertions.assertFalse(Validator.validEmail("test"));
        Assertions.assertFalse(Validator.validEmail("test@.com"));
        Assertions.assertFalse(Validator.validEmail("test@com"));
    }

    @Test
    void isValidNumber() {
        Assertions.assertTrue(Validator.isValidNumber("1"));
    }

    @Test
    void isNotValidNumber() {
        Assertions.assertFalse(Validator.isValidNumber("a"));
    }

    @Test
    void isValidLetter() {
        Assertions.assertTrue(Validator.isValidLetter("a"));
    }

    @Test
    void isNotValidLetter() {
        Assertions.assertFalse(Validator.isValidLetter("!"));
    }

    @Test
    void containsOnlyLetters() {
        Assertions.assertTrue(Validator.containsOnlyLetters("this contains only letters"));
    }

    @Test
    void notContainsOnlyLetters() {
        Assertions.assertFalse(Validator.containsOnlyLetters("this text doesn't contains only letters"));
    }
}