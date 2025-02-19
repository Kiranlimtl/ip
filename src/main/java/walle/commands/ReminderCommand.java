package walle.commands;

import java.io.IOException;
import java.time.LocalDate;

import walle.exceptions.WallException;
import walle.storage.Storage;
import walle.tasks.Deadline;
import walle.tasks.Event;
import walle.tasks.TaskList;
import walle.ui.Ui;
/**
 * Represents a command to remind the user of upcoming deadlines.
 */
public class ReminderCommand extends Command {
    /**
     * Creates a new ReminderCommand object.
     */
    public ReminderCommand() {
    }
    /**
     * Executes the reminder command.
     *
     * @param taskList The list of tasks
     * @param ui The user interface
     * @param storage The storage
     * @return The response to the user
     * @throws WallException If an error occurs
     * @throws IOException If an error occurs
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WallException, IOException {
        LocalDate currentDate = LocalDate.now();
        TaskList reminderTaskList = new TaskList();
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            if (taskList.getTask(i) instanceof Deadline) {
                Deadline curr = (Deadline) taskList.getTask(i);
                if (curr.getBy().toLocalDate().equals(currentDate)) {
                    reminderTaskList.addTask(curr);
                }
            } else if (taskList.getTask(i) instanceof Event) {
                Event curr = (Event) taskList.getTask(i);
                if (curr.getFrom().toLocalDate().equals(currentDate)
                        || curr.getTo().toLocalDate().equals(currentDate)) {
                    reminderTaskList.addTask(curr);
                }
            }
        }
        return ui.printReminders(reminderTaskList);
    }
}
