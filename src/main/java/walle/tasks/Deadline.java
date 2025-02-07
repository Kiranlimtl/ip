package walle.tasks;

import walle.parsers.DateTimeUtility;

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
     * Returns the by attribute of Deadline instance
     * @return
     */
    public LocalDateTime getBy() {
        return by;
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