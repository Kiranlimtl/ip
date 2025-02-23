package walle.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import walle.exceptions.WallException;
import walle.storage.Storage;
import walle.tasks.TaskList;
import walle.ui.Ui;

public class AddCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("test_tasks.txt"); // Adjust the path as needed for testing
    }

    @Test
    public void testAddToDo() throws WallException, IOException {
        AddCommand command = new AddCommand("Buy groceries", "todo");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.getTasks().size());
        assertEquals("Buy groceries", taskList.getTasks().get(0).getDescription());
    }

    @Test
    public void testAddDeadline() throws WallException, IOException {
        AddCommand command = new AddCommand("Submit assignment /by 25/12/2023 2359", "deadline");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0).toString().contains("Submit assignment"));
    }

    @Test
    public void testAddEvent() throws WallException, IOException {
        AddCommand command = new AddCommand("Project meeting /from 25/12/2023 2359 /to 25/3/2025 2359", "event");
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0).toString().contains("Project meeting"));
    }

    @Test
    public void testInvalidDeadlineFormat() {
        AddCommand command = new AddCommand("Submit assignment /by invalid-date", "deadline");

        Exception exception = assertThrows(WallException.class, () -> command.execute(taskList, ui, storage));
        assertEquals("Invalid date time format.", exception.getMessage());
    }
}
