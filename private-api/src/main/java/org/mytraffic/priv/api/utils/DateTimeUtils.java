package org.mytraffic.priv.api.utils;

import org.mytraffic.priv.api.PrivateApiConstants;

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
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern(PrivateApiConstants.DATE_TIME_FORMAT));
        } else {
            return null;
        }
    }

    public static final String formatTime(LocalTime time) {
        if (time != null) {
            return time.format(DateTimeFormatter.ofPattern(PrivateApiConstants.TIME_FORMAT));
        } else {
            return null;
        }
    }

}
