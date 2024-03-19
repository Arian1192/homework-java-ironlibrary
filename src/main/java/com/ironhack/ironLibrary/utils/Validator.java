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

}
