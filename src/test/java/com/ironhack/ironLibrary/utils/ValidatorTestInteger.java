package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTestInteger {


    @Test
    public void testValidateIntegerWithValidInput() {
        Validator validator = new Validator();
        String validInput = "12345";
        assertTrue(validator.validateInteger(validInput));
    }

    @Test
    public void testValidateIntegerWithInvalidInput() {
        Validator validator = new Validator();
        String invalidInput = "abc";
        String invalidInput2 = "226,98";
        assertFalse(validator.validateInteger(invalidInput));
        assertFalse(validator.validateInteger(invalidInput2));
    }

    @Test
    public void testValidateNonEmptyIntegerWithEmptyInput() {
        Validator validator = new Validator();
        assertFalse(validator.validateInteger(""));
    }

    @Test
    public void testValidateNonEmptyIntegerWithNullInput() {
        Validator validator = new Validator();
        assertFalse(validator.validateInteger(null));
    }


}
