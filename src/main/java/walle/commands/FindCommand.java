package walle.commands;

import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.ui.Ui;

/**
 * FindCommand class, to handle finding tasks
 */
public class FindCommand extends Command {
    private String keyword;
    /**
     * Constructor for FindCommand class
     *
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Finds tasks that contain the keyword
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList foundTasks = taskList.findTasks(keyword);
        return ui.printFoundTasks(foundTasks);
    }
}
