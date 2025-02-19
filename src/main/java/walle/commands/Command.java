package walle.commands;

import java.io.IOException;

import walle.exceptions.WallException;
import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.ui.Ui;
/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage object.
     * @throws WallException If an error occurs while executing the command.
     * @throws IOException   If an error occurs while writing to the storage file.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws WallException, IOException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return False by default.
     */
    public boolean isExit() {
        return false;
    }
}
