package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param taskList The task list to list tasks from.
     * @param ui The user interface to print messages.
     * @param storage The storage to save the task list to.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList);
    }
}