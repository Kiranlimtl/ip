package walle.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
