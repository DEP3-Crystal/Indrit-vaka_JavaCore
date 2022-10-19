package com.crystal.hangman.exeptions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ExceptionStackTraceTest {
    @Test
    void testStackTrace() {
        try {
            try {
                Integer.parseInt("a1");
            } catch (NumberFormatException ex) {
                //throw ex;
                NumberFormatException not_valid_number = new NumberFormatException("not valid number");
                not_valid_number.setStackTrace(ex.getStackTrace());
                throw not_valid_number;
            }

        } catch (Exception e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            System.out.println(e.getMessage());
        }

    }
}
