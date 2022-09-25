package com.crystal.atm.security;

import java.util.regex.Pattern;

public abstract class Validator {
    public static boolean isValidEmail(String email) {
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email).find();
    }
    public static boolean canExtractName(String email ){
        return email.split("@")[0].contains(".");
    }
    public static boolean isValidNumber(String number) {
        return Pattern.compile("^[0-9]+$").matcher(number).find();
    }

    public static boolean isValidLetter(String c) {
        return Pattern.compile("[A-z]|[0-9]", Pattern.CASE_INSENSITIVE).matcher(c).find();
    }

    public static boolean containsOnlyLetters(String nickName) {
        return Pattern.compile("^[a-zA-Z\\s]*$").matcher(nickName).find();
    }
}
