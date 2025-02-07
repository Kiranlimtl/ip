package walle.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for handling date-time conversions
 */
public class DateTimeUtility {
    private static final List<String> DATE_TIME_FORMATS = new ArrayList<String>();

    static {
        DATE_TIME_FORMATS.add("d/M/yyyy HHmm");
        DATE_TIME_FORMATS.add("d/M/yyyy HH:mm");
        DATE_TIME_FORMATS.add("d/M/yyyy HH");
        DATE_TIME_FORMATS.add("d-M-yyyy HHmm");
        DATE_TIME_FORMATS.add("d-M-yyyy HH:mm");
        DATE_TIME_FORMATS.add("d-M-yyyy HH");
        DATE_TIME_FORMATS.add("yyyy-MM-dd HH:mm");
    }

    /**
     * Parses a date-time string using multiple allowed formats.
     * @param dateTimeString The date-time string to parse.
     * @return Parsed LocalDateTime object or null if parsing fails.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        for (String format : DATE_TIME_FORMATS) {
            try {
                return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        System.out.println("Invalid date time format");
        return null;
    }

    /**
     * Formats a LocalDateTime object into a specific string format.
     * The format used is "MMM dd yyyy h:mm a".
     * @param dateTime The LocalDateTime object to format.
     * @return Formatted date-time string.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "Invalid date";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return dateTime.format(formatter);
    }

}