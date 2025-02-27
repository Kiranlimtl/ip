package walle.commands;


import java.io.IOException;

import walle.exceptions.WallException;
import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.ui.Ui;
/**
 * Represents a command to unmark a task as done
 */
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
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WallException {
        taskList.unmarkTask(index);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            ui.showError("I/O error: " + e.getMessage());
        }
        return ui.printUnmarkTask(taskList, index);
    }
}
