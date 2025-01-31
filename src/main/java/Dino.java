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
            System.out.println(i+1 + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
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
            } else if (line.startsWith("mark ")) {
                markItem(tasks, line);
            } else if (line.startsWith("unmark ")) {
                unmarkItem(tasks, line);
            } else {
                printLine();
                tasks[size] = new Task(line);
                size++;
                System.out.println("added: " + line);
                printLine();
            }
        }

        sayBye();
    }
}
