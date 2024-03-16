package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidatorTest {

    @ParameterizedTest
    @DisplayName("Should validate correct ISBN")
    @ValueSource(strings = {"978-92-95-055-02-5", "971-52-15-055-02-5", "971-52-15-755-12-3"} )
    void isCheckISBNFormatValid_when_correct_ISBN(String isbn) {
        assertTrue(Validator.checkISBNFormat(isbn));
    }

    @ParameterizedTest
    @DisplayName("Should invalidate incorrect ISBN")
    @ValueSource(strings = {"a78-92-952-055-02-5", "p71-52-1sd-055-02-5", "xxxs-52-15-755-12-0w"} )
    void isCheckISBNFormatNotValid_when_incorrect_ISBN(String isbn) {
        assertFalse(Validator.checkISBNFormat(isbn));
    }
}