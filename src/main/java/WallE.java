import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDateTime;

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
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new WallE("./data/walle.txt").run();
    }

}

