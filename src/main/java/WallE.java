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

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                printWithLine("Bye. Hope to see you again soon!");
                break;
            }
            //Handle List input
            if (userInput.equalsIgnoreCase("list")) {
                printTasks(tasks, curr);
            //Handle mark and umark input
            } else if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                String[] inputParts = userInput.split(" ");
                int target = Integer.parseInt(inputParts[1]) - 1;
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
                //Handle Todo
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);  // Remove "todo " prefix
                tasks[curr] = new ToDo(description);
                printAddedTask(tasks[curr], curr);
                curr++;
                //Handle deadline
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                if (parts.length == 2) {
                    String description = parts[0];
                    String by = parts[1];
                    tasks[curr] = new Deadline(description, by);
                    printAddedTask(tasks[curr], curr);
                    curr++;
                } else {
                    System.out.println("\tWrong format loser");
                }
                //Handle event
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                if (parts.length == 3) {
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    tasks[curr] = new Event(description, from, to);
                    printAddedTask(tasks[curr], curr);
                    curr++;
                } else {
                    System.out.println("\tWrong format loser");
                }
            } else if (userInput.isEmpty()) {
                continue;
            } else {
                tasks[curr] = new Task(userInput);
                printWithLine("added: " + tasks[curr].toString());
                curr++;
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
        System.out.println("\tNow you have " + curr + " tasks in the list.");
        System.out.println();
        printHorizontalLine();
    }


}
