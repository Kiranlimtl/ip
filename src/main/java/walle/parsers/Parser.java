package walle.parsers;

import walle.commands.AddCommand;
import walle.commands.ByeCommand;
import walle.commands.Command;
import walle.commands.DeleteCommand;
import walle.commands.FindCommand;
import walle.commands.ListCommand;
import walle.commands.MarkCommand;
import walle.commands.UnmarkCommand;
import walle.exceptions.WallException;

public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * @param userInput The raw input from the user.
     * @return The corresponding Command object.
     * @throws WallException If the command is invalid.
     */
    public static Command parse(String userInput) throws WallException {
        // Split user input by space to extract the command keyword and arguments
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0].trim();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "list":
            return new ListCommand();

        case "todo":
            if (arguments.isEmpty()) {
                throw new WallException("The description of a todo cannot be empty.");
            }
            return new AddCommand(arguments, "todo");

        case "deadline":
            if (arguments.isEmpty()) {
                throw new WallException("The description of a deadline cannot be empty.");
            }
            return new AddCommand(arguments, "deadline");

        case "event":
            if (arguments.isEmpty()) {
                throw new WallException("The description of an event cannot be empty.");
            }
            return new AddCommand(arguments, "event");

        case "mark":
            return new MarkCommand(parseTaskIndex(arguments));

        case "unmark":
            return new UnmarkCommand(parseTaskIndex(arguments));

        case "delete":
            return new DeleteCommand(parseTaskIndex(arguments));

        case "bye":
            return new ByeCommand();
            
        case "find":
            return new FindCommand(arguments);

        default:
            throw new WallException("I'm sorry, I don't understand that command.");
        }
    }

    /**
     * Parses the task index from the user input.
     *
     * @param argument The argument string.
     * @return The parsed task index.
     * @throws WallException If the index is not valid.
     */
    private static int parseTaskIndex(String argument) throws WallException {
        try {
            int index = Integer.parseInt(argument.trim());
            if (index <= 0) {
                throw new WallException("Task number must be greater than zero.");
            }
            return index - 1;  // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new WallException("Invalid task number. Please enter a valid number.");
        }
    }
}
