package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.tasks.Task;
import walle.exceptions.WallException;
import walle.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command to exit the program.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true
     *
     * @return True
     */
    public boolean isExit() {
        return true;
    }
}