package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.tasks.Task;
import walle.exceptions.WallException;
import walle.ui.Ui;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList);
    }
}