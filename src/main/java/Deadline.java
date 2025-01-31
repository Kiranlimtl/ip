/**
 * Deadline class, subclass of Task
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline class
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
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