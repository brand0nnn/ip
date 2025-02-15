package dino;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import dino.exception.DinoException;
import dino.exception.ExceptionMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dino {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void drawDino(String message) {
        System.out.println("               __");
        System.out.println("              / ^_)    " + message);
        System.out.println("     _.----._/ /");
        System.out.println("    /         /");
        System.out.println(" __/ (  | (  |");
        System.out.println("/__.-'|_|--|_|");
    }

    public static void sayHello() {
        printLine();
        drawDino("Hello! I'm Dino :)");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayBye() {
        printLine();
        drawDino("Byeee");
        printLine();
    }

    public static void displayList(ArrayList<Task> tasks) {
        int size = tasks.size();
        printLine();
        for (int i = 0; i < size; i++) {
            String date = tasks.get(i).getDate();
            System.out.println(i+1 + ".[" + tasks.get(i).getTypeIcon() + "][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description + date);
        }
        printLine();
    }

    public static void markItem(ArrayList<Task> tasks, String line, boolean mark) throws DinoException {
        printLine();
        String number = line.split(" ", 2)[1].trim();
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
        printLine();
    }

    public static void addItem(ArrayList<Task> tasks, String line, Type type) throws DinoException {
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
            tasks.add(new Deadline(task, parts[1].trim()));
            break;
        case EVENT:
            if (line.split(" ", 2)[1].trim().isEmpty()) {
                throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
            }
            parts = line.split("/from", 2);
            task = parts[0].substring(6).trim();
            String[] dates = parts[1].trim().split("/to", 2);
            if (dates[1].trim().isEmpty()) {
                throw new DinoException(ExceptionMessage.NO_TO_DATE);
            }
            tasks.add(new Event(task, dates[0].trim(), dates[1].trim()));
            break;
        }
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + tasks.get(tasks.size() - 1).getTypeIcon() + "][ ] " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    public static void deleteItem(ArrayList<Task> tasks, String line) throws DinoException {
        printLine();
        String number = line.split(" ", 2)[1].trim();
        int val = Integer.parseInt(number);
        if (val > tasks.size()) {
            throw new DinoException(ExceptionMessage.INVALID_INDEX);
        }
        System.out.println("Got it. I've removed this task:");
        System.out.println("  [" + tasks.get(val-1).getTypeIcon() + "][" + tasks.get(val-1).getStatusIcon() + "] " + tasks.get(val-1).description + tasks.get(val-1).getDate());
        tasks.remove(val-1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    public static void saveToFile(String filePath, ArrayList<Task> tasks) {
        File dir = new File("./data");
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.out.println("Error: Failed to make directory");
                return;
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.getTypeIcon() + "|" + task.getStatusIcon() + "|" + task.description + "|" + task.getStartDate() + "|" + task.getEndDate() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void loadFile(String filePath, ArrayList<Task> tasks) {
        File f = new File(filePath);
        if (!f.exists()) {
            return;
        }
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine().trim();
                String[] parts = line.split("\\|", 5);
                switch (parts[0]) {
                case "T" -> tasks.add(new Todo(parts[2]));
                case "D" -> tasks.add(new Deadline(parts[2], parts[4]));
                case "E" -> tasks.add(new Event(parts[2], parts[3], parts[4]));
                }
                if (parts[1].equals("X")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }

    public static void main(String[] args) {
        sayHello();

        ArrayList<Task> tasks = new ArrayList<>();
        boolean isExit = false;
        String line;
        Scanner in = new Scanner(System.in);
        String filePath = "data/savefile.txt";
        loadFile(filePath, tasks);

        while (!isExit) {
            line = in.nextLine();
            if (line.equals("bye")) {
                isExit = true;
            } else if (line.equals("list")) {
                displayList(tasks);
            } else if (line.startsWith("todo ")) {
                try {
                    addItem(tasks, line, Type.TODO);
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Check whether your command is correct: todo {task name}");
                }
            } else if (line.startsWith("deadline ")) {
                try {
                    addItem(tasks, line, Type.DEADLINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("INPUT ERROR: dino.Deadline end date not specified.");
                    System.out.println("Check whether your command is correct: deadline {task name} /by {end date/time}");
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Check whether your command is correct: deadline {task name} /by {end date/time}");
                }
            } else if (line.startsWith("event ")) {
                try {
                    addItem(tasks, line, Type.EVENT);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("INPUT ERROR: Improper start and end date specified.");
                    System.out.println("Check whether your command is correct: event {event name} /from {start date/time} /to {end date/time}");
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Check whether your command is correct: event {event name} /from {start date/time} /to {end date/time}");
                }
            } else if (line.startsWith("mark ")) {
                try {
                    markItem(tasks, line, true);
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("INPUT ERROR: Item number specified does not exist on the list.");
                } catch (NumberFormatException e) {
                    System.out.println("INPUT ERROR: No index specified");
                }
            } else if (line.startsWith("unmark ")) {
                try {
                    markItem(tasks, line, false);
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("INPUT ERROR: Item number specified does not exist on the list.");
                }
            } else if (line.startsWith("delete ")) {
                try {
                    deleteItem(tasks, line);
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("INPUT ERROR: Invalid index specified");
                }
            } else {
                printLine();
                System.out.println("Im sorry, I don't understand what you mean by that");
                printLine();
            }
        }
        saveToFile(filePath, tasks);
        sayBye();
    }
}
