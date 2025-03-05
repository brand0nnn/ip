package dino;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import dino.exception.DinoException;
import dino.exception.ExceptionMessage;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

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
            task = parts[0].substring(6).trim();
            String[] dates = parts[1].trim().split("/to", 2);
            if (dates[1].trim().isEmpty()) {
                throw new DinoException(ExceptionMessage.NO_TO_DATE);
            }
            tasks.add(new Event(task, LocalDateTime.parse(dates[0].trim(), formatter), LocalDateTime.parse(dates[1].trim(), formatter)));
            break;
        }
        ui.printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + tasks.get(tasks.size() - 1).getTypeIcon() + "][ ] " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.printLine();
    }

    public void deleteTask(String line) throws DinoException {
        ui.printLine();
        String number = line.split(" ", 2)[1].trim();
        if (number.isEmpty()) {
            throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
        }
        int val = Integer.parseInt(number);
        if (val > tasks.size()) {
            throw new DinoException(ExceptionMessage.INVALID_INDEX);
        }
        System.out.println("Got it. I've removed this task:");
        System.out.println("  [" + tasks.get(val-1).getTypeIcon() + "][" + tasks.get(val-1).getStatusIcon() + "] " + tasks.get(val-1).description + tasks.get(val-1).getDate());
        tasks.remove(val-1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.printLine();
    }

    public void markOrUnmarkTask(String line, boolean mark) throws DinoException {
        ui.printLine();
        String number = line.split(" ", 2)[1].trim();
        if (number.isEmpty()) {
            throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
        }
        int val = Integer.parseInt(number);
        if (mark && tasks.get(val-1).isDone) {
            throw new DinoException(ExceptionMessage.ITEM_MARKED);
        }
        if (!mark && !tasks.get(val-1).isDone) {
            throw new DinoException(ExceptionMessage.ITEM_UNMARKED);
        }
        if (mark) {
            tasks.get(val-1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [X] " + tasks.get(val-1).description);
        } else {
            tasks.get(val-1).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + tasks.get(val-1).description);
        }
        ui.printLine();
    }
}
