package com.shu.backend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverterUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static String localDatetime2String(LocalDateTime date) {
        return date.format(formatter);
    }

    public static LocalDateTime string2LocalDateTime(String date) {
        return LocalDateTime.parse(date, formatter);
    }
}
