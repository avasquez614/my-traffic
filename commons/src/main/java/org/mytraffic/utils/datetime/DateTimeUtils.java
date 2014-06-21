package org.mytraffic.utils.datetime;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods for dates and times.
 *
 * @author avasquez
 * @author mariobarque
 */
public class DateTimeUtils {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
    public static final String TIME_FORMAT = "HH:mm:ss";

    private DateTimeUtils() {
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    }

    public static DateTimeFormatter getTimeFormatter() {
        return DateTimeFormatter.ofPattern(TIME_FORMAT);
    }

    public static String formatDateTime(ZonedDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.format(getDateTimeFormatter());
        } else {
            return null;
        }
    }

    public static String formatTime(LocalTime time) {
        if (time != null) {
            return time.format(getTimeFormatter());
        } else {
            return null;
        }
    }

    public static ZonedDateTime parseDateTime(String dateTime) {
        if (StringUtils.isNotEmpty(dateTime)) {
            return ZonedDateTime.parse(dateTime, getDateTimeFormatter());
        } else {
            return null;
        }
    }

    public static LocalTime parseTime(String time) {
        if (StringUtils.isNotEmpty(time)) {
            return LocalTime.parse(time, getTimeFormatter());
        } else {
            return null;
        }
    }

}
