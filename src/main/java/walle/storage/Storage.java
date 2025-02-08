package walle.storage;

import walle.tasks.Task;
import walle.tasks.TaskList;
import walle.tasks.ToDo;
import walle.tasks.Deadline;
import walle.tasks.Event;
import walle.exceptions.CorruptedDataException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to read and write the ArrayList from/to a seperate file
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadTasks() throws CorruptedDataException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null) {
                parentDir.mkdirs();  // Create parent directories if needed
            }
            file.createNewFile();  // Create the file
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
        if (parts.length > 4) {
            throw new CorruptedDataException("too many  | | | | ");
        }
        String type = parts[0];
        boolean isDone;
        if (!type.equals("T") && !type.equals("D") && !type.equals("E")) {
            throw new CorruptedDataException("Task type not known");
        }
        try {
            isDone = parts[1].equals("1");
        } catch (Exception e) {
            throw new CorruptedDataException("Why number so weird");
        }
        String description = parts[2];
        if (description.isEmpty()) {
            throw new CorruptedDataException("Your old task got no description");
        }

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
            Event event  = new Event(description, eventParts[0].trim(), eventParts[1].trim());

            if (isDone == true) {
                event.markAsDone();
            }
            return event;
        default:
            throw new IllegalArgumentException("Unknown task type in file");
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
