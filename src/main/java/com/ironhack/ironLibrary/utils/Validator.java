package com.ironhack.ironLibrary.utils;


import java.util.regex.Pattern;

public class Validator {

    public static boolean checkISBNFormat(String maybeISBN){
        return patternMatches(maybeISBN, "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");
    }

    public static boolean checkEmailFormat(String email){
        return patternMatches(email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }



    private static boolean patternMatches(String inputString, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(inputString)
                .matches();
    }

    public static boolean validateInteger(String input) {
        try {
            if (input == null || input.trim().isEmpty()) {
                return false;
            }
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean notBlankValidatorBooks(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean validateStringGeneralFormat(String input) {
        return input != null && !input.isEmpty() && patternMatches(input, "^[a-zA-ZÀ-ÿ\\s]*$");
    }
}


