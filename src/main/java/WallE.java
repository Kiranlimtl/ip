import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class WallE {
    private static final String FILE_PATH = "./data/walle.txt";
    private static  Storage storage;
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        storage = new Storage(FILE_PATH);

        try {
            tasks = storage.loadTasks();
        } catch (CorruptedDataException e) {
            printWithLine(e.getMessage());
            return;
        } catch (IOException e) {
            printWithLine("I/O error: " + e.getMessage());
            return;
        }

        printWithLine("Hello! I'm WallE.\n" +
                "\tWhat can I do for you?");

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                printWithLine("Write something pls!");
                continue;
            }

            if (userInput.equalsIgnoreCase("bye")) {
                printWithLine("Bye. Hope to see you again soon!");
                break;
            }

            try {
                Command command = Command.fromString(userInput);
                switch (command) {
                case LIST:
                    printTasks(tasks);
                    break;
                case MARK:
                case UNMARK:
                    handleMarkAndUnmarkedTask(tasks, userInput);
                    storage.saveTasks(tasks);
                    break;
                case DELETE:
                    deleteTask(tasks, userInput);
                    storage.saveTasks(tasks);
                    break;
                case TODO:
                case EVENT:
                case DEADLINE:
                    handleAddedTask(tasks, userInput);
                    storage.saveTasks(tasks);
                    break;
                default:
                    printWithLine("Invalid task type lololol!!!");
                    break;
                }

            } catch (WallException | IOException e) {
                printWithLine("OOPS WallE_rror!!!! " + e.getMessage());
            }

        }
        scanner.close();
    }


    private static void printHorizontalLine(){
        System.out.println("\t--------------------------------------------------");
    }

    private static void printWithLine(String input){
        printHorizontalLine();
        System.out.println("\t" + input);
        System.out.println();
        printHorizontalLine();
    }
    private static void printTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            printWithLine("No tasks found");
        } else {
            printHorizontalLine();
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
            }
            System.out.println();
            printHorizontalLine();
        }

    }
    private static void printAddedTask(Task task, int curr){
        printHorizontalLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + curr + " tasks in the list.");
        System.out.println();
        printHorizontalLine();
    }
    private static void handleMarkAndUnmarkedTask(ArrayList<Task> tasks, String userInput) throws WallException {
        Command command = Command.fromString(userInput);
        String commandKeyword = command == Command.MARK ? "mark" : "unmark";
        if (userInput.length() == commandKeyword.length() || userInput.substring(commandKeyword.length()).trim().isEmpty()) {
            throw new WallException("What do you want to " + (commandKeyword) + " huh?");
        }
        String afterCommand = (command == Command.MARK) ? userInput.substring(5).trim() : userInput.substring(7).trim();

        int target;
        String[] parts = afterCommand.split(" ");
        try {
            target = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new WallException("Invalid task number. Please enter a valid number.");
        }

        if (target < 0 || target >= tasks.size()) {
            throw new WallException("You know that is not even in the list.");
        } else if (parts.length > 1) {
            throw new WallException("I can only " + (commandKeyword) + " one thing and you know it!!");
        }

        switch (command) {
            case MARK:
                tasks.get(target).markAsDone();
                System.out.println("\tNice! I've marked this task as done:\n\t" + tasks.get(target));
                break;
            case UNMARK:
                tasks.get(target).unmarkAsNotDone();
                System.out.println("\tOK, I've marked this task as not done yet:\n\t" + tasks.get(target));
                break;
            default:
                throw new WallException("Invalid command.");
        }
        System.out.println();
        printHorizontalLine();

        }
    private static void handleAddedTask(ArrayList<Task> tasks, String userInput) throws WallException {
        Command command = Command.fromString(userInput);
        switch (command) {
            case TODO:
                if (userInput.length() <= 5) throw new WallException("Todo what?");
                tasks.add(new ToDo(userInput.substring(5).trim()));
                break;
            case DEADLINE:
                if (userInput.length() <= 9) throw new WallException("Deadline? Cmon.");
                String[] deadlineParts = userInput.substring(9).split(" /by ");
                if (deadlineParts.length != 2) throw new WallException("Invalid deadline format.");
                tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                break;
            case EVENT:
                if (userInput.length() <= 6) throw new WallException("Event? Pls?");
                String[] eventParts = userInput.substring(6).split(" /from | /to ");
                if (eventParts.length != 3) throw new WallException("Invalid event format.");
                tasks.add(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
                break;
            default:
                break;
        }

        printAddedTask(tasks.get(tasks.size() - 1), tasks.size());

    }
    private static void deleteTask(ArrayList<Task> tasks, String userInput) throws WallException{
        if (userInput.length() <= 7) {
            throw new WallException("Idk what you want me delete bruh!");
        }
        int target;
        String[] parts = userInput.substring(7).split(" ");
        try {
            target = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new WallException("Invalid task number. Please enter a valid number.");
        }
        if (target >= tasks.size()) {
            throw new WallException("You know that is not even in the list");
        } else if (target < 0 || tasks.get(target) == null || parts.length > 1) {
            throw new WallException("Give me something to delete please!!");
        }
        printHorizontalLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + tasks.get(target).toString());
        tasks.remove(target);
        System.out.println("\tNow you have " + tasks.size() + " in the list");
        System.out.println();
        printHorizontalLine();
    }
}

/**
 * Exception for WallE
 */
class WallException extends Exception {
    WallException(String message) {
        super(message);
    }
}

/**
 * Enumeration for the different cases
 */
enum Command {
    TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, INVALID;

    // Helper to determine the command type from user input
    public static Command fromString(String input) {
        if (input.startsWith("todo")) return TODO;
        if (input.startsWith("deadline")) return DEADLINE;
        if (input.startsWith("event")) return EVENT;
        if (input.startsWith("mark")) return MARK;
        if (input.startsWith("unmark")) return UNMARK;
        if (input.startsWith("delete")) return DELETE;
        if (input.equalsIgnoreCase("list")) return LIST;
        return INVALID;
    }
}
