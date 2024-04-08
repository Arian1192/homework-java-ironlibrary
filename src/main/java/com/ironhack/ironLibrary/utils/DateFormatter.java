package com.ironhack.ironLibrary.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {

    public static String simpleDateFormat(LocalDateTime returnDate){
        DateTimeFormatter formatDate1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return returnDate.format(formatDate1);
    }

    public static String largeDateFormat(LocalDateTime returnDate){
        DateTimeFormatter formatDate2 = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy",
                Locale.ENGLISH);
        ZonedDateTime zonedDateTime = returnDate.atZone(ZoneId.of("Europe/Helsinki"));
        return zonedDateTime.format(formatDate2);
    }
}
