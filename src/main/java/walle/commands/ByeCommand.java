package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.tasks.Task;
import walle.exceptions.WallException;
import walle.ui.Ui;

public class ByeCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}