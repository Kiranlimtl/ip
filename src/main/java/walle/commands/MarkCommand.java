package walle.commands;

import java.io.IOException;

import walle.exceptions.WallException;
import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.ui.Ui;
/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor for MarkCommand class
     *
     * @param index
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WallException {
        taskList.markTask(index);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            ui.showError("I/O error: " + e.getMessage());
        }
        return ui.printMarkTask(taskList, index);
    }
}
