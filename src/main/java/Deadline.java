import java.util.ArrayList;
import java.time.LocalDateTime;


/**
 * Deadline class, subclass of Task
 */

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for Deadline class
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateTimeUtility.parseDateTime(by);
    }

    /**
     * String representation of Deadline instance
     * @return
     */
    @Override
    public String toString() {
        if (by == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + DateTimeUtility.formatDateTime(by) + ")";
        }
    }
}