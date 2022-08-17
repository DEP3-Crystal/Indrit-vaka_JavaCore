package com.crystal.ramdom_person.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validEmail(String email){
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(email);

        return m.find();
    }
}
