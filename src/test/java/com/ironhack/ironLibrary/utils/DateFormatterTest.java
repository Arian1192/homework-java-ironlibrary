package com.ironhack.ironLibrary.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateFormatterTest {
    @Test
    @DisplayName("Validate proper format date: yyyy-MM-dd HH:mm:ss")
    void simpleDateFormatTest() {
        LocalDateTime time = LocalDateTime.now();
        assertEquals(time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                DateFormatter.simpleDateFormat(time));
    }

    @Test
    @DisplayName("Validate proper format date: EEE MMM dd HH:mm:ss zzz yyyy")
    void largeDateFormatTest() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy",
                Locale.ENGLISH);
        assertEquals(time.atZone(ZoneId.of("Europe/Helsinki")).format(formatDate),
                DateFormatter.largeDateFormat(time));
    }
}
