package walle.ui;

import java.util.ArrayList;
import java.util.Scanner;

import walle.exceptions.WallException;
import walle.tasks.Task;
import walle.tasks.TaskList;


/**
 * Represents the user interface of WallE.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     ** Prints the welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm WallE.\n" + "What can I do for you?";
    }
    /**
     * Prints the goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }
    /**
     * Prints the error message.
     *
     * @param message The error message
     */
    public String showError(String message) {
        return message;
    }
    /**
     * Prints the tasks in the task list
     *
     * @param taskList The list of tasks
     */
    public String printTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String allTask = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            allTask += "\n" + "\t" + (i + 1) + ". " + tasks.get(i).toString();
        }
        allTask += "\n";
        allTask += printHorizontalLine();
        return allTask;
    }
    /**
     * Prints the task when ListCommand is executed
     *
     * @param taskList The list of tasks
     */
    public String printListTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String allTask = "";
        if (tasks.isEmpty()) {
            return ("No tasks found");
        } else {
            allTask += "Here are the tasks in your list:";
            allTask += printTasks(taskList);
        }
        return allTask;
    }
    /**
     * Prints the added task
     *
     * @param taskList The list of tasks
     * @param task The task to add
     */
    public String printAddedTask(TaskList taskList, Task task) {
        String addedTask = "Got it. I've added this task:";
        addedTask += "\n \t" + task.toString();
        addedTask += ("\nNow you have " + taskList.getSize() + " tasks in the list.");
        return addedTask;
    }
    /**
     * Prints the mark task
     *
     * @param taskList The list of tasks
     * @param index The index of the task to mark
     */
    public String printMarkTask(TaskList taskList, int index) throws WallException {
        String markedTask = "Nice! I've marked this task as done:";
        markedTask += "\n \t" + printWithLine(taskList.getTask(index).toString());
        return markedTask;
    }
    /**
     * Prints the unmark task
     *
     * @param taskList The list of tasks
     * @param index The index of the task to unmark
     */
    public String printUnmarkTask(TaskList taskList, int index) throws WallException {
        String unmarkedTask = "Nice! I've unmarked this task:";
        unmarkedTask += "\n \t" + printWithLine(taskList.getTask(index).toString());
        return unmarkedTask;
    }
    /**
     * Prints the delete task
     *
     * @param taskList The list of tasks
     * @param task The task to delete
     */
    public String printDeleteTask(TaskList taskList, Task task) {
        String deleteTask = ("Noted. I've removed this task:");
        deleteTask += "\n \t" + task.toString();
        int actSize = taskList.getSize() - 1;
        deleteTask += "\n" + "Now you have " + actSize + " in the list";
        return deleteTask;
    }
    /**
     * Prints the tasks found
     *
     * @param taskList The list of tasks
     */
    public String printFoundTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "No tasks found" + "\n" + printHorizontalLine();
        }
        String foundTask = ("Here are the matching tasks in your list:");
        for (Task tasks : taskList.getTasks()) {
            foundTask += "\n \t" + tasks.toString();
        }
        foundTask += "\n" + printHorizontalLine();
        return foundTask;
    }
    /**
     * Prints the horizontal line
     */
    @SuppressWarnings("checkstyle:Indentation")
    public String printHorizontalLine() {
        return "--------------------------------------------------";
    }
    /**
     * Prints the input with a horizontal line
     *
     * @param input The input to print
     */
    public String printWithLine(String input) {
        String string = input;
        string += "\n";
        string += printHorizontalLine();
        return string;
    }
    /**
     * Prints the reminders
     *
     * @param taskList The list of tasks
     */
    public String printReminders(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return printWithLine("No upcoming deadlines or events");
        }
        return "Here are the tasks due today" + printTasks(taskList);
    }
}
