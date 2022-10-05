package com.crystal.atm.security;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.regex.Pattern;

public class Validator {
    public boolean isValidEmail(String email) {
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email).find();
    }

    public boolean canExtractName(String email) {
        return email.split("@")[0].contains(".");
    }

    //TODO allow negative numbers
    public boolean isValidNumber(String number) {
        return Pattern.compile("^[0-9]+$").matcher(number).find();
    }

    public boolean isValidLetter(String c) {
        return Pattern.compile("[A-z]|[0-9]", Pattern.CASE_INSENSITIVE).matcher(c).find();
    }

    public boolean containsOnlyLetters(String nickName) {
        return Pattern.compile("^[a-zA-Z\\s]*$").matcher(nickName).find();
    }

    public boolean isNotValidYear(int year) {
        return year < LocalDate.MIN.getYear() || year > LocalDate.MAX.getYear();
    }

    public boolean isNotValidMonth(int month) {
        return month < 0 || month > 12;
    }

    public boolean isNotValidDay(int year, int month, int day) {
        return day < 1 || day > getNumberOfDaysInMonth(year, month);
    }

    // Method to get number of days in month
    public int getNumberOfDaysInMonth(int year, int month) {
        return YearMonth.of(year, month).lengthOfMonth();
    }
}
