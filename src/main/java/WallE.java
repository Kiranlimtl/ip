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
            //Handle mark input
            } else if (userInput.startsWith("mark ")) {
                String[] inputParts = userInput.split(" ");
                int target = Integer.parseInt(inputParts[1]) - 1;
                tasks[target].markAsDone();
                printHorizontalLine();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + tasks[target].toString());
            //Handle unmark input
            } else if (userInput.startsWith("unmark ")) {
                String[] inputParts = userInput.split(" ");
                int target = Integer.parseInt(inputParts[1]) - 1;
                tasks[target].unmarkAsNotDone();
                printHorizontalLine();
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t" + tasks[target].toString());
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
        System.out.println();
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


}
