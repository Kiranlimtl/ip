package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.tasks.Task;
import walle.exceptions.WallException;
import walle.ui.Ui;

import java.io.IOException;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WallException {       
        taskList.markTask(index);
        ui.printMarkTask(taskList, index);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            ui.showError("I/O error: " + e.getMessage());
        }
    }
}