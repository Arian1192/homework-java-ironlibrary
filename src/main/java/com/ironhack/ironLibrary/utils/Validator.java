package com.ironhack.ironLibrary.utils;


import java.util.Scanner;
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

    /**
     * TODO Testing
     * @param printText
     * @param validTheNextInput
     * @param methodName
     * @param suggestedInputFormat
     * @return
     */
    public static String userInput(String printText, boolean validTheNextInput, String methodName, String suggestedInputFormat){
        String userInputText;
        boolean result = false;
        do{
            System.out.print(printText);
            Scanner scanner = new Scanner(System.in);
            userInputText = scanner.nextLine();
            if(validTheNextInput){
                try{
                    switch(methodName){
                        case "checkISBNFormat":
                            result = Validator.checkISBNFormat(userInputText);
                            break;
                        case "validateStringGeneralFormat":
                            result = Validator.validateStringGeneralFormat(userInputText);
                            break;
                        case "checkEmailFormat":
                            result = Validator.checkEmailFormat(userInputText);
                            break;
                        case "validateInteger":
                            result = Validator.validateInteger(userInputText);
                            break;
                        case "notBlankValidatorBooks":
                            result = Validator.notBlankValidatorBooks(userInputText);
                            break;
                    }
                    if(!result){
                        System.out.println("Sorry, but the input format it's incorrect. Please try again: ");
                        System.out.println(suggestedInputFormat);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                result = true;
            }
        }while(!result);
        return userInputText;
    };
}


