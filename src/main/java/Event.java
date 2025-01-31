/**
 * Event class, subclass of Task
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for Event class
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
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