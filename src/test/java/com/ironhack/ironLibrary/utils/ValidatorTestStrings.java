package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTestStrings {

    @Test
    public void testNotBlankValidatorBooksValidInput() {
        String input = "The Book Title";
        assertTrue(Validator.notBlankValidatorBooks(input));
    }

    @Test
    public void testNotBlankValidatorBooksNullInput() {
        String input = null;
        assertFalse(Validator.notBlankValidatorBooks(input));
    }

    @Test
    public void testNotBlankValidatorBooksEmptyInput() {
        String input = "";
        assertFalse(Validator.notBlankValidatorBooks(input));
    }

    @Test
    public void testNotBlankValidatorBooksWhitespaceInput() {
        String input = "   ";
        assertFalse(Validator.notBlankValidatorBooks(input));
    }

    @Test
    public void testValidateStringGeneralFormatWithValidInput() {
        String input = "John Doe";
        assertTrue(Validator.validateStringGeneralFormat(input));
    }

    @Test
    public void testValidateStringGeneralFormatWithAccentedCharacters() {
        String input = "José García";
        assertTrue(Validator.validateStringGeneralFormat(input));
    }

    @Test
    public void testValidateStringGeneralFormatWithInvalidInput() {
        String input = "123 Main Street";
        assertFalse(Validator.validateStringGeneralFormat(input));
    }

    @Test
    public void testValidateStringGeneralFormatWithEmptyInput() {
        String input = "";
        assertFalse(Validator.validateStringGeneralFormat(input));
    }

    @Test
    public void testValidateStringGeneralFormatWithNullInput() {
        String input = null;
        assertFalse(Validator.validateStringGeneralFormat(input));
    }
}