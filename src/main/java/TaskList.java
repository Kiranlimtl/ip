import java.util.ArrayList;

/**
 * TaskList class, to handle operations on tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list
     *
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list
     *
     * @param index
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as done
     *
     * @param index
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task as done
     *
     * @param index
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmarkAsNotDone();
    }

    /**
     * Returns the task list
     *
     * @return
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

}