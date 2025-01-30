import java.util.Scanner;

public class WallE {
    public static void main(String[] args) {
        //Create new scanner
        Scanner scanner = new Scanner(System.in);

        //Greetings
        System.out.println("\tHello! I'm WallE.");
        System.out.println("\tWhat can I do for you?");
        System.out.println();
        printHorizontalLine();
        System.out.println();
        System.out.println();

        while (true) {
            String userInput = scanner.nextLine();

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                printHorizontalLine();
                System.out.println("\tBye. Hope to see you again soon!");
                printHorizontalLine();
                break;
            }

            // Echo the user's input
            printHorizontalLine();
            System.out.println("\t" + userInput);
            System.out.println();
            printHorizontalLine();
            System.out.println();
            System.out.println();
        }

        //Close the scanner
        scanner.close();
    }

    //Method to print horizontal line
    private static void printHorizontalLine(){
        System.out.println("\t--------------------------------------------------");
    }

}
