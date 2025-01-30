import java.util.ArrayList;
import java.util.Scanner;

public class WallE {
    public static void main(String[] args) {
        //Create new scanner
        Scanner scanner = new Scanner(System.in);

        //Create 100 element String Array
        ArrayList<Task> tasks = new ArrayList<Task>();
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
                    printTasks(tasks);
                } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                    handleMarkAndUnmarkedTask(tasks, userInput);
                } else if (userInput.startsWith("delete")){
                    deleteTask(tasks, userInput);
                } else {
                    handleAddedTask(tasks, userInput);
                }
            } catch (WallException e) {
                printWithLine("OOPS WallE_rror!!!! " + e.getMessage());
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
        if (userInput.startsWith("mark")) {
            if (userInput.length() <= 5) {
                throw new WallException("What do you want to mark huh?");
            }
            int target;
            String[] parts = userInput.substring(5).split(" ");
            try {
                target = Integer.parseInt(parts[0]) - 1;
            } catch (NumberFormatException e) {
                throw new WallException("Invalid task number. Please enter a valid number.");
            }
            if (target >= tasks.size()) {
                throw new WallException("You know that is not even in the list");
            } else if (target < 0 || tasks.get(target) == null || parts.length > 1) {
                throw new WallException("Invalid task number. No such task exists.");
            }
            tasks.get(target).markAsDone();
            printHorizontalLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + tasks.get(target).toString());
            System.out.println();
            printHorizontalLine();
        } else if (userInput.startsWith("unmark")) {
            if (userInput.length() <= 7) {
                throw new WallException("What do you want to unmark huh?");
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
                throw new WallException("Invalid task number. No such task exists.");
            }
            tasks.get(target).unmarkAsNotDone();
            printHorizontalLine();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t" + tasks.get(target).toString());
            System.out.println();
            printHorizontalLine();
        }


    }
    private static void handleAddedTask(ArrayList<Task> tasks, String userInput) throws WallException {
        if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5) {  // Input length is too short
                throw new WallException("Todo what huh?");
            }
            String description = userInput.substring(5);
            if (description.isEmpty()) {
                throw new WallException("Write something lol.");
            }
            tasks.add(new ToDo(description));
            printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
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
            tasks.add(new Deadline(description, by));
            printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
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
            tasks.add(new Event(description, from, to));
            printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
        } else {
            tasks.add(new Task(userInput));
            printWithLine("added: " + tasks.get(tasks.size() - 1).toString());
        }
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

class WallException extends Exception {
    WallException(String message) {
        super(message);
    }
}