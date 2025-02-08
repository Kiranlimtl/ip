package walle.tasks;

/**
 * Todo class, subclass of Task
 */
public class ToDo extends Task {

    /**
     * Constructor for Todo class
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of Todo instance
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}