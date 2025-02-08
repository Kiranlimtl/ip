package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.tasks.Task;
import walle.ui.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index < 0 || index >= taskList.getSize()) {
            ui.showError("Invalid task number");
            return;
        }
        Task task = taskList.getTasks().get(index);
        taskList.deleteTask(index);
        ui.printDeleteTask(taskList, task, index);
    }
}