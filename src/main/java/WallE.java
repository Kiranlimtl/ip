import java.util.Scanner;

public class WallE {
    public static void main(String[] args) {
        //Create new scanner
        Scanner scanner = new Scanner(System.in);

        //Create 100 element String Array
        String[] tasks = new String[100];
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
            if (userInput.equalsIgnoreCase("list")) {
                printTasks(tasks, curr);
            } else {
                tasks[curr] = userInput;
                curr++;
                printWithLine("added: " + userInput);
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

    private static void printTasks(String[] tasks, int curr){
        if (curr == 0) {
            printWithLine("No tasks found");
        } else {
            printHorizontalLine();
            for (int i = 0; i < curr; i++){
                System.out.println("\t" + (i + 1) + ". " + tasks[i]);
            }
            System.out.println();
            printHorizontalLine();
        }

    }


}
