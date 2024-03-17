package com.ironhack.ironLibrary.utils;


import java.util.regex.Pattern;

public class Validator {

    public static boolean checkISBNFormat(String maybeISBN){
        return patternMatches(maybeISBN, "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");
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
}
