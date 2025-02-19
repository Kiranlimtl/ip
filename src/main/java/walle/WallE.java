package walle;

import java.io.IOException;

import walle.commands.Command;
import walle.commands.ReminderCommand;
import walle.exceptions.CorruptedDataException;
import walle.parsers.Parser;
import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.ui.Ui;
/**
 * Represents the main class of WallE.
 */
public class WallE {
    private static final String FILE_PATH = "./data/walle.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    /**
     * Constructor for WallE
     */
    public WallE() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            taskList = storage.loadTasks();
        } catch (CorruptedDataException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        } catch (IOException e) {
            ui.showError("I/O error: " + e.getMessage());
        }
    }
    /**
     * Method to get response to show in Ui
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    /**
     * Method to get welcome message
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
    /**
     * Method to get reminder message
     */
    public String getReminderMessage() {
        Command reminder = new ReminderCommand();
        try {
            return reminder.execute(taskList, ui, storage);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    /**
     * Method to check if the input is an exit command
     */
    public boolean isExitCommand(String input) {
        try {
            Command command = Parser.parse(input);
            return command.isExit();
        } catch (Exception e) {
            return false;
        }
    }
}

