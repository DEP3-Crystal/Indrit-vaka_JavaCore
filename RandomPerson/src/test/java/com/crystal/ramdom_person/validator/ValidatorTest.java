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
    public void checkInValidEmail(){
        Assertions.assertFalse(Validator.validEmail("test"));
        Assertions.assertFalse(Validator.validEmail("test@.com"));
        Assertions.assertFalse(Validator.validEmail("test@com"));
    }
}