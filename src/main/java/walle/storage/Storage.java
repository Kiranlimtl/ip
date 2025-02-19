package walle.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import walle.exceptions.CorruptedDataException;
import walle.tasks.Deadline;
import walle.tasks.Event;
import walle.tasks.Task;
import walle.tasks.TaskList;
import walle.tasks.ToDo;
/**
 * Class to read and write the ArrayList from or to a seperate file
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage
     * @param filePath the path of the file to read and write
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the tasks from the file
     * @return the TaskList object
     * @throws CorruptedDataException if the data in the file is corrupted
     * @throws IOException if there is an error reading the file
     */
    public TaskList loadTasks() throws CorruptedDataException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null) {
                parentDir.mkdirs();
            }
            file.createNewFile();
            return new TaskList(tasks);
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            tasks.add(parseTask(line));
        }
        reader.close();
        return new TaskList(tasks);
    }

    /**
     * Save the tasks to the file
     * @param taskList the TaskList object to save
     * @throws IOException if there is an error writing to the file
     */
    public void saveTasks(TaskList taskList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            writer.write(formatTask(task));
            writer.newLine();
        }
        writer.close();
    }

    private Task parseTask(String line) throws CorruptedDataException {
        String[] parts = line.split(" \\| ");
        validateTaskData(parts);
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            if (parts.length > 3) {
                throw new CorruptedDataException("ToDo wrong");
            }
            ToDo todo = new ToDo(description.trim());
            if (isDone == true) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (parts.length > 4) {
                throw new CorruptedDataException("Deadline wrong");
            }
            Deadline deadline = new Deadline(description, parts[3].trim());
            if (isDone == true) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            if (parts.length > 4) {
                throw new CorruptedDataException("Event wrong");
            }
            String[] eventParts = parts[3].split("-", 2);
            if (eventParts.length != 2) {
                throw new IllegalArgumentException("Invalid event format in file.");
            }
            Event event = new Event(description, eventParts[0].trim(), eventParts[1].trim());

            if (isDone == true) {
                event.markAsDone();
            }
            return event;
        default:
            throw new IllegalArgumentException("Unknown task type in file");
        }
    }

    private void validateTaskData(String[] parts) throws CorruptedDataException {
        if (parts.length > 4) {
            throw new CorruptedDataException("Too many '|' in line.");
        }
        if (!parts[0].equals("T") && !parts[0].equals("D") && !parts[0].equals("E")) {
            throw new CorruptedDataException("Unknown task type.");
        }
        if (parts[2].isEmpty()) {
            throw new CorruptedDataException("Task description is missing.");
        }
    }

    private String formatTask(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof ToDo) {
            sb.append("T | ").append(task.isDone() ? "1" : "0").append(" | ").append(task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append("D | ").append(deadline.isDone() ? "1" : "0").append(" | ").append(deadline.getDescription())
                    .append(" | ").append(deadline.getBy());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            sb.append("E | ").append(event.isDone() ? "1" : "0").append(" | ").append(event.getDescription())
                    .append(" | ").append(event.getFrom()).append("-").append(event.getTo());
        }
        return sb.toString();
    }
}
