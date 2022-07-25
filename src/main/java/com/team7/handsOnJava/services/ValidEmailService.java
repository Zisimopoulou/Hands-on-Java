package com.team7.handsOnJava.services;

import java.util.regex.Pattern;
//It only checks the presence of the @ symbol in the email address.
//If present, then the validation result returns true, otherwise, the result is false
public class ValidEmailService {
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}
