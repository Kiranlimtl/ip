package walle;

import walle.commands.Command;
import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.exceptions.CorruptedDataException;
import walle.ui.Ui;
import walle.parsers.Parser;

import java.io.IOException;


public class WallE {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public WallE(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.loadTasks();
        } catch (CorruptedDataException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        } catch (IOException e) {
            ui.showError("I/O error: " + e.getMessage());
        }
    }

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
    
    public static void main(String[] args) {
        new WallE("./data/walle.txt").run();
    }

}

