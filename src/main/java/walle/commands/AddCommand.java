package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.tasks.ToDo;
import walle.tasks.Deadline;
import walle.tasks.Event;
import walle.exceptions.WallException;
import walle.ui.Ui;
import walle.parsers.DateTimeUtility;

import java.time.LocalDateTime;
import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final String argument;
    private final String taskType;

    /** Creates a new AddCommand object with the given argument and task type.
     *
     * @param argument
     * @param taskType
     */
     public AddCommand(String argument, String taskType) {
         this.argument = argument;
         this.taskType = taskType;
     }
     /**
      * Executes the add command.
      *
      * @param taskList The task list to add the task to.
      * @param ui The user interface to print messages.
      * @param storage The storage to save the task list.
      * @throws WallException If the task type is invalid or the argument is invalid.
      * @throws IOException If there is an error saving the task list.
      */
     public String execute(TaskList taskList, Ui ui, Storage storage) throws WallException, IOException {
         assert taskType.equalsIgnoreCase("todo") || taskType.equalsIgnoreCase("deadline") || taskType.equalsIgnoreCase("event") : "Invalid task type: " + taskType;         
         switch (taskType.toLowerCase()) {
         case "todo":
             return handleToDo(taskList, ui, storage);
         case "deadline":
             return handleDeadline(taskList, ui, storage);
         case "event":
             return handleEvent(taskList, ui, storage);
         default:
             return "Failed to add task.";
         }
     }

    private String handleToDo(TaskList taskList, Ui ui, Storage storage) throws WallException, IOException {
        if (argument.length() <= 0) throw new WallException("Todo what?");
        ToDo todo = new ToDo(argument.trim());
        taskList.addTask(todo);
        storage.saveTasks(taskList);
        return ui.printAddedTask(taskList, todo);
    }

    private String handleDeadline(TaskList taskList, Ui ui, Storage storage) throws WallException, IOException {
        if (argument.length() <= 0) throw new WallException("Deadline? Cmon.");
        String[] deadlineParts = argument.split(" /by ");
        if (deadlineParts.length != 2) throw new WallException("Invalid deadline format.");
        LocalDateTime by = DateTimeUtility.parseDateTime(deadlineParts[1].trim());
        if (by == null) throw new WallException("Invalid date time format.");
        Deadline deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        taskList.addTask(deadline);
        storage.saveTasks(taskList);
        return ui.printAddedTask(taskList, deadline);
    }

    private String handleEvent(TaskList taskList, Ui ui, Storage storage) throws WallException, IOException {
        if (argument.length() <= 0) throw new WallException("Event? Pls?");
        String[] eventParts = argument.split(" /from | /to ");
        if (eventParts.length != 3) throw new WallException("Invalid event format.");
        LocalDateTime from = DateTimeUtility.parseDateTime(eventParts[1].trim());
        LocalDateTime to = DateTimeUtility.parseDateTime(eventParts[2].trim());
        if (from == null || to == null) throw new WallException("Invalid date time format for event.");
        Event event = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
        taskList.addTask(event);
        storage.saveTasks(taskList);
        return ui.printAddedTask(taskList, event);
     }
 }

