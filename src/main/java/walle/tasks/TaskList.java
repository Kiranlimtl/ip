package walle.tasks;

import java.util.ArrayList;

import walle.exceptions.WallException;
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
    public void deleteTask(int index) throws WallException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new WallException("I can't delete something that doesn't exist.");
        }
    }

    /**
     * Marks a task as done
     *
     * @param index
     */
    public void markTask(int index) throws WallException {
        try {
            tasks.get(index).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new WallException("I can't mark something that doesn't exist.");
        }
    }

    /**
     * Unmarks a task as done
     *
     * @param index
     */
    public void unmarkTask(int index) throws WallException {
        try {
            tasks.get(index).unmarkAsNotDone();
        } catch (IndexOutOfBoundsException e) {
            throw new WallException("I can't unmark something that doesn't exist.");
        }
    }

    /**
     * Returns the task list
     *
     * @return
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    /**
     * Finds tasks with a keyword
     *
     * @param keyword
     * @return
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    public Task getTask(int index) throws WallException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new WallException("I can't get something that doesn't exist.");
        }
    }

    public int getSize() {
        return tasks.size();
    }

}
