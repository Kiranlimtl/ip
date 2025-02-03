import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Event class, subclass of Task
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

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
     * Constructor for Event class
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
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
     * String representation of Event instance
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}