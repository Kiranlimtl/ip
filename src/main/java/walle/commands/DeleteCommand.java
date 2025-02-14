package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.tasks.Task;
import walle.ui.Ui;
import walle.exceptions.WallException;

import java.io.IOException;

/**
 * Represents a delete command
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand class
     *
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WallException, IOException {
        if (index < 0 || index >= taskList.getSize()) {
            ui.showError("Invalid task number");
            return;
        }
        Task task = taskList.getTasks().get(index);
        ui.printDeleteTask(taskList, task, index);
        taskList.deleteTask(index);
        storage.saveTasks(taskList);
    }
}