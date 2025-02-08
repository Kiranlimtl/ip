package walle.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Test task");
        task.markAsDone();
        assertTrue(task.isDone(), "Task should be marked as done.");
    }

    @Test
    public void testUnmarkAsNotDone() {
        Task task = new Task("Test task");
        task.markAsDone();
        task.unmarkAsNotDone();
        assertFalse(task.isDone(), "Task should be marked as not done.");
    }
}
