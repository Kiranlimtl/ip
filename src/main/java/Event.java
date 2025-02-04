import java.util.ArrayList;
import java.time.LocalDateTime;
/**
 * Event class, subclass of Task
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event class
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeUtility.parseDateTime(from);
        this.to = DateTimeUtility.parseDateTime(to);
    }


    /**
     * String representation of Event instance
     * @return
     */
    @Override
    public String toString() {
        if (from == null || to == null) {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        } else {
            return "[E]" + super.toString() + " (from: " + DateTimeUtility.formatDateTime(from) + " to: " + DateTimeUtility.formatDateTime(to) + ")";
        }
    }
}