package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;




@SpringBootTest
public class ValidatorUserInputTest {

    @Test
    public void testUserInputWithValidISBN() {

        String expectedInput = "978-84-415-4302-0";
        String printText = "Enter isbn: ";
        String methodName = "checkISBNFormat";
        String suggestedInputFormat = "The ISBN must follow the next format: 978-92-95055-02-5";

        InputStream inputStream = new ByteArrayInputStream(expectedInput.getBytes());
        System.setIn(inputStream);

        String userInput = Validator.userInput(printText, true, methodName, suggestedInputFormat);

        assertEquals(expectedInput, userInput);
    }

}
