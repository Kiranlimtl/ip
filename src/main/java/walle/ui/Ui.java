package walle.ui;

import walle.exceptions.WallException;
import walle.tasks.Task;
import walle.tasks.TaskList;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the user interface of WallE.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    
    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm WallE.\n" + "\tWhat can I do for you?";
    }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showError(String message) {
        return message;
    }

    public String printTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String all_task = "";
        if (tasks.size() == 0) {
            return ("No tasks found");
        } else {
            all_task += "\tHere are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                all_task += "\n" + "\t" + (i + 1) + "." + tasks.get(i).toString();
            }
            all_task += "\n";
            all_task += printHorizontalLine();
            return all_task;
        }

    }

    public String printAddedTask(TaskList taskList, Task task) {
        String addedTask = "Got it. I've added this task:";
        addedTask += "\n" + "\t" + task.toString();
        addedTask += ("\nNow you have " + taskList.getSize() + " tasks in the list.");
        return addedTask;
    }

    public String printMarkTask(TaskList taskList, int index) throws WallException {
        String markedTask = "\tNice! I've marked this task as done:";
        markedTask += "\n" + printWithLine(taskList.getTask(index).toString());
        return markedTask;
    }

    public String printUnmarkTask(TaskList taskList, int index) throws WallException {
        String unmarkedTask = "\tNice! I've unmarked this task:";
        unmarkedTask += "\n" + printWithLine(taskList.getTask(index).toString());
        return unmarkedTask;
    }

    public String printDeleteTask(TaskList taskList, Task task) {
        String deleteTask = ("\tNoted. I've removed this task:");
        deleteTask += "\n" + "\t" + task.toString();
        int act_size = taskList.getSize() - 1;
        deleteTask += "\n" + "Now you have " + act_size + " in the list";
        return deleteTask;
    }
    
    public String printFoundTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "\tNo tasks found" + "\n" + printHorizontalLine();
        }
        String foundTask = ("\tHere are the matching tasks in your list:");
        for (Task tasks : taskList.getTasks()) {
            foundTask += "\n" + "\t" + tasks.toString();
        }
        foundTask += "\n" + printHorizontalLine();
        return foundTask;
    }
    /**
     * Prints the horizontal line
     */
    @SuppressWarnings("checkstyle:Indentation")
    public String printHorizontalLine() {
        return "\t--------------------------------------------------";
    }

    public String printWithLine(String input) {
        String string = "\t" + input;
        string += "\n";
        string += printHorizontalLine();
        return string;
    }

    public void close() {
        scanner.close();
    }
}
