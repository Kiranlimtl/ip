package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.tasks.Task;
import walle.exceptions.WallException;
import walle.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor for UnmarkCommand class
     *
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks a task as done
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkTask(index);
        ui.printUnmarkTask(taskList, index);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            ui.showError("I/O error: " + e.getMessage());
        }
    }
}