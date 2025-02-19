package walle.commands;

import java.io.IOException;

import walle.exceptions.WallException;
import walle.storage.Storage;
import walle.tasks.Task;
import walle.tasks.TaskList;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WallException, IOException {
        if (index < 0 || index >= taskList.getSize()) {
            return ui.showError("Invalid task number");
        }
        Task task = taskList.getTasks().get(index);
        String temp = ui.printDeleteTask(taskList, task);
        taskList.deleteTask(index);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            ui.showError("I/O error: " + e.getMessage());
        }
        return temp;
    }
}
