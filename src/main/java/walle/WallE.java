package walle;

import java.io.IOException;

import walle.commands.Command;
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
     * Main method to run WallE
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
    /**
     * Main method to run WallE
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

