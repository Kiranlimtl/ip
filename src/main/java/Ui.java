import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        printWithLine("Hello! I'm WallE.\n" +
                "\tWhat can I do for you?");
    }

    public void showGoodbye() {
        printWithLine("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        printWithLine(message);
    }

    public void printTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.size() == 0) {
            printWithLine("No tasks found");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
            }
            System.out.println();
            printHorizontalLine();
        }

    }

    public void printAddedTask(TaskList taskList, Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + task.toString());
        printWithLine("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public void printMarkTask(TaskList taskList, int index) {
        System.out.println("\tNice! I've marked this task as done:");
        printWithLine(taskList.getTask(index).toString());
    }

    public void printUnmarkTask(TaskList taskList, int index) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        printWithLine(taskList.getTask(index).toString());;
    }

    public void printDeleteTask(TaskList taskList, Task task, int index) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task.toString());
        taskList.deleteTask(index);
        printWithLine("Now you have " + taskList.getTasks().size() + " in the list");
    }

    public void printHorizontalLine(){
        System.out.println("\t--------------------------------------------------");
    }

    public void printWithLine(String input){
        System.out.println("\t" + input);
        System.out.println();
        printHorizontalLine();
    }

    public void close() {
        scanner.close();
    }
}