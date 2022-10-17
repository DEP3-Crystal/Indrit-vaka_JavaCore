package com.crystal.hangman.secirity;

import java.util.regex.Pattern;
public abstract class Validation {
    /**
     * @param number The num as string
     * @return true if its a positive or negative number
     */
    public static boolean isValidNumber(String number) {
        return Pattern.compile("^[+-]?[0-9]{1,9}(?:\\.[0-9]{1,2})?$").matcher(number).find();
    }

    /**
     * @param c the letter to be checked
     * @return true if the string is single letter no special characters allowed
     */
    public static boolean isValidLetter(String c) {
        return Pattern.compile("^\\w{1,1}$", Pattern.CASE_INSENSITIVE).matcher(c).find();
    }
    public static boolean isValidNickName(String nickName){
        return Pattern.compile("^\\w+$").matcher(nickName).find();
    }
    public static boolean isValidPassword(String password){
        return Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{8,}$").matcher(password).find();
    }

}
