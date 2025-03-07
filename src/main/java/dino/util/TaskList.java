package dino.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

import dino.tasks.Type;
import dino.exception.DinoException;
import dino.exception.ExceptionMessage;
import dino.tasks.Deadline;
import dino.tasks.Event;
import dino.tasks.Task;
import dino.tasks.Todo;

/**
 * Represents a list storing all the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList using the specified ArrayList of Task
     *
     * @param tasks An ArrayList of Task used to construct the TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an ArrayList of Task containing all the tasks.
     *
     * @return All tasks as an ArrayList of Task
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a specified task into TaskList.
     *
     * @param line The user input command
     * @param type The type of task to be added
     * @throws DinoException If the command is empty, or if no date is specified for Deadline and Event type tasks
     */
    public void addTask(String line, Type type) throws DinoException {
        String task = "";
        String[] parts;
        switch (type) {
        case TODO:
            task = line.split(" ", 2)[1].trim();
            if (task.isEmpty()) {
                throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
            }
            tasks.add(new Todo(task));
            break;
        case DEADLINE:
            if (line.split(" ", 2)[1].trim().isEmpty()) {
                throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
            }
            parts = line.split("/by", 2);
            if (parts.length == 1) {
                throw new DinoException(ExceptionMessage.NO_BY_DATE);
            }
            task = parts[0].substring(9).trim();
            if (parts[1].trim().isEmpty()) {
                throw new DinoException(ExceptionMessage.NO_BY_DATE);
            }
            LocalDate date = LocalDate.parse(parts[1].trim());
            tasks.add(new Deadline(task, date));
            break;
        case EVENT:
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (line.split(" ", 2)[1].trim().isEmpty()) {
                throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
            }
            parts = line.split("/from", 2);
            if (parts.length == 1) {
                throw new DinoException(ExceptionMessage.NO_FROM_DATE);
            }
            task = parts[0].substring(6).trim();
            String[] dates = parts[1].trim().split("/to", 2);
            if (dates.length == 1) {
                throw new DinoException(ExceptionMessage.NO_TO_DATE);
            }
            if (dates[1].trim().isEmpty()) {
                throw new DinoException(ExceptionMessage.NO_TO_DATE);
            }
            tasks.add(new Event(task, LocalDateTime.parse(dates[0].trim(), formatter), LocalDateTime.parse(dates[1].trim(), formatter)));
            break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + tasks.get(tasks.size() - 1).getTypeIcon() + "][ ] " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Delete the specified task from TaskList
     *
     * @param line The user input command
     * @throws DinoException If the command is empty or the specified task number does not exist
     */
    public void deleteTask(String line) throws DinoException {
        String number = line.split(" ", 2)[1].trim();
        if (number.isEmpty()) {
            throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
        }
        int val = Integer.parseInt(number);
        if (val > tasks.size()) {
            throw new DinoException(ExceptionMessage.INVALID_INDEX);
        }
        System.out.println("Got it. I've removed this task:");
        System.out.println("  [" + tasks.get(val-1).getTypeIcon() + "][" + tasks.get(val-1).getStatusIcon() + "] " + tasks.get(val-1).getDescription() + tasks.get(val-1).getDate());
        tasks.remove(val-1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks or unmarks the specified task in TaskList.
     *
     * @param line The user input command
     * @param mark True for mark, false for unmark
     * @throws DinoException If the command is empty, or if the item has already been marked/unmarked
     */
    public void markOrUnmarkTask(String line, boolean mark) throws DinoException, IndexOutOfBoundsException {
        String number = line.split(" ", 2)[1].trim();
        if (number.isEmpty()) {
            throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
        }
        int val = Integer.parseInt(number);
        if (mark && tasks.get(val-1).getStatus()) {
            throw new DinoException(ExceptionMessage.ITEM_MARKED);
        }
        if (!mark && !tasks.get(val-1).getStatus()) {
            throw new DinoException(ExceptionMessage.ITEM_UNMARKED);
        }
        if (mark) {
            tasks.get(val-1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [X] " + tasks.get(val-1).getDescription());
        } else {
            tasks.get(val-1).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + tasks.get(val-1).getDescription());
        }
    }
}
