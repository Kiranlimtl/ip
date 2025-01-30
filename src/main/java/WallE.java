import java.util.ArrayList;
import java.util.Scanner;

public class WallE {
    public static void main(String[] args) {
        //Create new scanner
        Scanner scanner = new Scanner(System.in);

        //Create 100 element String Array
        Task[] tasks = new Task[100];
        int curr = 0;

        //Greetings
        printWithLine("Hello! I'm WallE.\n" +
                "\tWhat can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();


            if (userInput.isEmpty()) {
                printWithLine("Write something pls!");
                continue;  // Skip empty input
            }

            if (userInput.equalsIgnoreCase("bye")) {
                printWithLine("Bye. Hope to see you again soon!");
                break;
            }

            try {
                if (userInput.equalsIgnoreCase("list")) {
                    printTasks(tasks, curr);
                } else if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                    handleMarkAndUnmarkedTask(tasks, curr, userInput);
                } else {
                    curr = handleAddedTask(tasks, curr, userInput);
                }
            } catch (WallException e) {
                printWithLine("OOPS!!! WallError!!!! " + e.getMessage());
            }

        }

        //Close the scanner
        scanner.close();
    }

    //Method to print horizontal line
    private static void printHorizontalLine(){
        System.out.println("\t--------------------------------------------------");
    }

    //Method to print other stuff with line
    private static void printWithLine(String input){
        printHorizontalLine();
        System.out.println("\t" + input);
        System.out.println();
        printHorizontalLine();
    }

    private static void printTasks(Task[] tasks, int curr) {
        if (curr == 0) {
            printWithLine("No tasks found");
        } else {
            printHorizontalLine();
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < curr; i++) {
                System.out.println("\t" + (i + 1) + "." + tasks[i].toString());
            }
            System.out.println();
            printHorizontalLine();
        }

    }

    private static void printAddedTask(Task task, int curr){
        printHorizontalLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + (curr + 1) + " tasks in the list.");
        System.out.println();
        printHorizontalLine();
    }


    private static void handleMarkAndUnmarkedTask(Task[] tasks, int curr, String userInput) throws WallException {
        String[] inputParts = userInput.split(" ");
        if (inputParts.length != 2) {
            throw new WallException("Invalid command format. Use: mark/unmark [task number]");
        }
        int target;
        try {
            target = Integer.parseInt(inputParts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new WallException("Invalid task number. Please enter a valid number.");
        }
        if (target < 0 || target >= curr || tasks[target] == null) {
            throw new WallException("Invalid task number. No such task exists.");
        }
        if (inputParts[0].equals("mark")) {
            tasks[target].markAsDone();
            printHorizontalLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + tasks[target].toString());
            System.out.println();
            printHorizontalLine();
        } else {
            tasks[target].unmarkAsNotDone();
            printHorizontalLine();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t" + tasks[target].toString());
            System.out.println();
            printHorizontalLine();
        }
    }
    private static int handleAddedTask(Task[] tasks, int curr, String userInput) throws WallException {
        if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5) {  // Input length is too short
                throw new WallException("Todo what huh?");
            }
            String description = userInput.substring(5);
            if (description.isEmpty()) {
                throw new WallException("Write something lol.");
            }
            tasks[curr] = new ToDo(description);
            printAddedTask(tasks[curr], curr);
            return curr+ + 1;
            //Handle deadline
        } else if (userInput.startsWith("deadline")) {
            if (userInput.length() <= 9) {  // Input length is too short
                throw new WallException("deadline what huh?");
            }
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new WallException("Invalid deadline format. Use: deadline [description] /by [time]");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            tasks[curr] = new Deadline(description, by);
            printAddedTask(tasks[curr], curr);
            return curr+ + 1;
            //Handle event
        } else if (userInput.startsWith("event")) {
            if (userInput.length() <= 6) {  // Input length is too short
                throw new WallException("what event huh?");
            }
            String[] parts = userInput.substring(6).split(" /from | /to ");
            if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new WallException("Invalid event format. Use: event [description] /from [time] /to [time]");
            }
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            tasks[curr] = new Event(description, from, to);
            printAddedTask(tasks[curr], curr);
            return curr+ + 1;
        } else {
            tasks[curr] = new Task(userInput);
            printWithLine("added: " + tasks[curr].toString());
            return curr+ + 1;
        }
    }
}

class WallException extends Exception {
    WallException(String message) {
        super(message);
    }
}