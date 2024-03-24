package com.shu.backend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverterUtil {

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static String localDatetime2String(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
