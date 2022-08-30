package com.crystal.hangman.secirity;

import java.util.regex.Pattern;
public abstract class Validation {
    public static boolean isValidNumber(String number) {
        return Pattern.compile("\\d").matcher(number).find();
    }

    public static boolean isValidLetter(String c) {
        return Pattern.compile("^[A-z]", Pattern.CASE_INSENSITIVE).matcher(c).find();
    }
    public static boolean isValidNickName(String nickName){
        return Pattern.compile("\\w").matcher(nickName).find();
    }
}
