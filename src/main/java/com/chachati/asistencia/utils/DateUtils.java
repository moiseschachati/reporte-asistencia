package com.chachati.asistencia.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtils {

    public DateUtils() {}

    public static LocalDateTime localDateFromTimestamp(Timestamp timestamp) {
        return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("UTC"));
    }
}
