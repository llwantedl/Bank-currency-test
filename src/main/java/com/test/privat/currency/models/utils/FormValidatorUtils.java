package com.test.privat.currency.models.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidatorUtils {
    public static boolean validate(String email) {
        Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.find();
    }
}
