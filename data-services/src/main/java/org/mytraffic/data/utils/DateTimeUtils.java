package org.mytraffic.data.utils;

import org.mytraffic.api.data.DataRestConstants;

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

    private DateTimeUtils() {
    }

    public static final String formatDateTime(ZonedDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DataRestConstants.DATE_TIME_FORMAT));
    }

    public static final String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(DataRestConstants.TIME_FORMAT));
    }

}