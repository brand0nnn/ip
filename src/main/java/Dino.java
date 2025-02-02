import java.util.Scanner;

public class Dino {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sayHello() {
        printLine();
        System.out.println("Hello! I'm Dino");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
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

    public static void markItem(Task[] tasks, String line) {
        printLine();
        String number = line.substring(5);
        int val = Integer.parseInt(number);
        if (tasks[val-1].isDone) {
            System.out.println("Oops, this item has already been marked!");
        } else {
            tasks[val-1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [X] " + tasks[val-1].description);
        }
        printLine();
    }

    public static void unmarkItem(Task[] tasks, String line) {
        printLine();
        String number = line.substring(7);
        int val = Integer.parseInt(number);
        if (!tasks[val-1].isDone) {
            System.out.println("Oops, this item is already unmarked!");
        } else {
            tasks[val-1].unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + tasks[val-1].description);
        }
        printLine();
    }

    public static void addItem(Task[] tasks, int size, String line, Type type) {
        int startIndex;
        String task = "";
        String sentence;
        switch (type) {
        case TODO:
            startIndex = 5;
            task = line.substring(startIndex);
            tasks[size] = new Todo(task);
            break;
        case DEADLINE:
            startIndex = 9;
            sentence = line.substring(startIndex);
            int end = sentence.indexOf("/by") - 1;
            task = sentence.substring(0, end);
            String date = sentence.substring(end + 5);
            tasks[size] = new Deadline(task, date);
            break;
        case EVENT:
            startIndex = 6;
            sentence = line.substring(startIndex);
            int startSlash = sentence.indexOf("/from");
            int endSlash = sentence.indexOf("/to");
            task = sentence.substring(0, startSlash - 1);
            String startDate = sentence.substring(startSlash + 6, endSlash - 1);
            String endDate = sentence.substring(endSlash + 4);
            tasks[size] = new Event(task, startDate, endDate);
            break;
        }
        printLine();
        System.out.println("Got it. I've added this task: ");
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
                addItem(tasks, size, line, Type.TODO);
                size++;
            } else if (line.startsWith("deadline ")) {
                addItem(tasks, size, line, Type.DEADLINE);
                size++;
            } else if (line.startsWith("event ")) {
                addItem(tasks, size, line, Type.EVENT);
                size++;
            } else if (line.startsWith("mark ")) {
                markItem(tasks, line);
            } else if (line.startsWith("unmark ")) {
                unmarkItem(tasks, line);
            } else {
                printLine();
                System.out.println("Im sorry, I don't understand what you mean by that");
                printLine();
            }
        }

        sayBye();
    }
}
