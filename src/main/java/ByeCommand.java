public class ByeCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}