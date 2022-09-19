package com.crystal.hangman.secirity;

import com.crystal.hangman.model.User;

import java.util.regex.Pattern;
public abstract class Validation {
    public static boolean isValidNumber(String number) {
        return Pattern.compile("\\d").matcher(number).find();
    }

    public static boolean isValidLetter(String c) {
        return Pattern.compile("[A-z]|[0-9]", Pattern.CASE_INSENSITIVE).matcher(c).find();
    }
    public static boolean isValidNickName(String nickName){
        return Pattern.compile("\\w").matcher(nickName).find();
    }
    public static boolean isValidPassword(String password){
        return Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{8,}$").matcher(password).find();
    }
    public static boolean isValidPassword(String password, User user){
        return password.equals(user.getPassword());
    }
}
