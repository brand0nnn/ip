import java.util.Scanner;

public class Dino {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void drawDino(String message) {
        System.out.println("               __");
        System.out.println("              / _)    " + message);
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

    public static void displayList(Task[] tasks, int size) {
        printLine();
        for (int i = 0; i < size; i++) {
            String date = tasks[i].getDate();
            System.out.println(i+1 + ".[" + tasks[i].getTypeIcon() + "][" + tasks[i].getStatusIcon() + "] " + tasks[i].description + date);
        }
        printLine();
    }

    public static void markItem(Task[] tasks, String line, boolean mark) throws DinoException {
        printLine();
        int startIndex = mark ? 5 : 7;
        String number = line.substring(startIndex);
        int val = Integer.parseInt(number);
        if (mark && tasks[val-1].isDone) {
            throw new DinoException(ExceptionMessage.ITEM_MARKED);
        }
        if (!mark && !tasks[val-1].isDone) {
            throw new DinoException(ExceptionMessage.ITEM_UNMARKED);
        }
        if (mark) {
            tasks[val-1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [X] " + tasks[val-1].description);
        } else {
            tasks[val-1].unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + tasks[val-1].description);
        }
        printLine();
    }

    public static void addItem(Task[] tasks, int size, String line, Type type) throws DinoException {
        String task = "";
        String[] parts;
        switch (type) {
        case TODO:
            task = line.split(" ", 2)[1].trim();
            if (task.isEmpty()) {
                throw new DinoException(ExceptionMessage.EMPTY_COMMAND);
            }
            tasks[size] = new Todo(task);
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
            tasks[size] = new Deadline(task, parts[1].trim());
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
            tasks[size] = new Event(task, dates[0].trim(), dates[1].trim());
            break;
        }
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + tasks[size].getTypeIcon() + "][ ] " + task);
        System.out.println("Now you have " + (size + 1) + " tasks in the list.");
        printLine();
    }

    public static void main(String[] args) {
        sayHello();

        Task[] tasks = new Task[100];
        int size = 0;
        boolean isExit = false;
        String line;
        Scanner in = new Scanner(System.in);

        while (!isExit) {
            line = in.nextLine();
            if (line.equals("bye")) {
                isExit = true;
            } else if (line.equals("list")) {
                displayList(tasks, size);
            } else if (line.startsWith("todo ")) {
                try {
                    addItem(tasks, size, line, Type.TODO);
                    size++;
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Check whether your command is correct: todo {task name}");
                }
            } else if (line.startsWith("deadline ")) {
                try {
                    addItem(tasks, size, line, Type.DEADLINE);
                    size++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("INPUT ERROR: Deadline end date not specified.");
                    System.out.println("Check whether your command is correct: deadline {task name} /by {end date/time}");
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Check whether your command is correct: deadline {task name} /by {end date/time}");
                }
            } else if (line.startsWith("event ")) {
                try {
                    addItem(tasks, size, line, Type.EVENT);
                    size++;
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
                }
            } else if (line.startsWith("unmark ")) {
                try {
                    markItem(tasks, line, false);
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("INPUT ERROR: Item number specified does not exist on the list.");
                }
            } else {
                printLine();
                System.out.println("Im sorry, I don't understand what you mean by that");
                printLine();
            }
        }

        sayBye();
    }
}
