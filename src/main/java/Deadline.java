import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class, subclass of Task
 */

public class Deadline extends Task {

    protected LocalDateTime by;

    private static final ArrayList<String> DATE_TIME_FORMATS = new ArrayList<String>();

    static {
        DATE_TIME_FORMATS.add("d/M/yyyy HHmm");
        DATE_TIME_FORMATS.add("d/M/yyyy HH:mm");
        DATE_TIME_FORMATS.add("d/M/yyyy HH");
        DATE_TIME_FORMATS.add("d/M/yyyy");
        DATE_TIME_FORMATS.add("d-M-yyyy HHmm");
        DATE_TIME_FORMATS.add("d-M-yyyy HH:mm");
        DATE_TIME_FORMATS.add("d-M-yyyy HH");
        DATE_TIME_FORMATS.add("d-M-yyyy");
        DATE_TIME_FORMATS.add("yyyy-MM-dd HH:mm");

    }
    /**
     * Constructor for Deadline class
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }
    private LocalDateTime parseDateTime(String dateTime) {
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
     * String representation of Deadline instance
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}