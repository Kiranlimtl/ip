package walle.tasks;

/**
 * Class to handle task given by user
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }



    /**
     * Mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark a done task
     */
    public void unmarkAsNotDone() {
        this.isDone = false;
    }

    /**
     * Get the visual representation of the
     * task if it is mark or unmark
     * @return "X" if task is mark and " " if unmark
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with x
    }

    /**
     * String representation of the Task instance
     * @return
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
