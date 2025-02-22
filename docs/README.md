# WallE User Guide

WallE is a simple and interactive task manager that helps users organize their tasks efficiently. It supports adding different types of tasks, marking tasks as done, and searching for tasks quickly.
Reminders are automatically send when starting the app for any tasks that are due within the next 24 hours.

## Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
   - [Adding a Task](#adding-a-task)
   - [Listing Tasks](#listing-tasks)
   - [Marking a Task as Done](#marking-a-task-as-done)
   - [Unmarking a Task](#unmarking-a-task)
   - [Finding a Task](#finding-a-task)
   - [Deleting a Task](#deleting-a-task)
   - [Setting a Reminder](#setting-a-reminder)
   - [Exiting the Program](#exiting-the-program)
4. [Saving and Editing Data](#saving-and-editing-data)
5. [FAQ](#faq)
6. [Command Summary](#command-summary)

---

## Introduction
WallE is a simple and interactive task manager that helps users organize their tasks efficiently. It supports adding different types of tasks, marking tasks as done, setting reminders, and searching for tasks quickly.

## Quick Start
1. Ensure that **Java 17 or later** is installed on your system.
2. Download the latest `walle.jar` file from the [releases page](https://github.com/your-repo/walle/releases).
3. Open a terminal and navigate to the folder containing `walle.jar`.
4. Run the application using the following command:
   ```sh
   java -jar walle.jar
   ```
5. WallE will launch with a command-line interface where you can start managing your tasks.

---

## Features

### Adding a Task
You can add different types of tasks:

- **ToDo Task:**
  ```sh
  todo [task description]
  ```
  Example:
  ```sh
  todo Read a book
  ```

- **Deadline Task:**
  ```sh
  deadline [task description] /by [dd-MM-yyyy HH:mm]
  ```
  Example:
  ```sh
  deadline Submit assignment /by 25-02-2025 23:59
  ```

- **Event Task:**
  ```sh
  event [task description] /from [dd-MM-yyyy HH:mm] /to [dd-MM-yyyy HH:mm]
  ```
  Example:
  ```sh
  event Project meeting /from 20-02-2025 14:00 /to 20-02-2025 16:00
  ```

### Listing Tasks
To display all the tasks stored in WallE, use:
```sh
list
```

### Marking a Task as Done
To mark a task as completed, use:
```sh
mark [task number]
```
Example:
```sh
mark 2
```
(Marks the second task as done)

### Unmarking a Task
To unmark a task (mark it as incomplete), use:
```sh
unmark [task number]
```
Example:
```sh
unmark 2
```

### Finding a Task
To search for a task by keyword, use:
```sh
find [keyword]
```
Example:
```sh
find assignment
```
(Lists all tasks containing "assignment")

### Deleting a Task
To remove a task from the list, use:
```sh
delete [task number]
```
Example:
```sh
delete 3
```
(Deletes the third task)

### Exiting the Program
To exit WallE, use:
```sh
bye
```

---

## Saving and Editing Data
WallE automatically saves all tasks in `data/walle.txt`. You can manually edit this file if necessary, but ensure that the format remains consistent.

---

## FAQ
**Q: How do I undo a command?**  
A: Currently, WallE does not support undoing commands. Be cautious when deleting tasks.

**Q: Can I use WallE on macOS/Linux?**  
A: Yes! As long as you have Java installed, WallE works on Windows, macOS, and Linux.

**Q: How do I reset all tasks?**  
A: Delete the `data/walle.txt` file and restart WallE.

---

## Command Summary
```
| Command                                                        | Description                 |
|----------------------------------------------------------------|-----------------------------|
| `todo [task]`                                                  | Adds a ToDo task            |
| `deadline [task] /by [dd-MM-yyyy HH:mm]`                       | Adds a Deadline task        |
| `event [task] /from [dd-MM-yyyy HH:mm] /to [dd-MM-yyyy HH:mm]` | Adds an Event task          |
| `list`                                                         | Lists all tasks             |
| `mark [task number]`                                           | Marks a task as done        |
| `unmark [task number]`                                         | Unmarks a completed task    |
| `find [keyword]`                                               | Finds tasks by keyword      |
| `delete [task number]`                                         | Deletes a task              |
| `bye`                                                          | Exits WallE                 |
```

---

Enjoy using WallE! 🚀