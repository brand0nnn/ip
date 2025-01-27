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

    public static void main(String[] args) {
        sayHello();

        String[] list = new String[100];
        boolean[] isChecked = new boolean[100];
        int size = 0;
        boolean isExit = false;
        String line;
        Scanner in = new Scanner(System.in);

        while (!isExit) {
            line = in.nextLine();
            if (line.equals("bye")) {
                isExit = true;
            } else if (line.equals("list")) {
                printLine();
                char mark;
                for (int i = 0; i < size; i++) {
                    if (isChecked[i]) {
                        mark = 'X';
                    } else {
                        mark = ' ';
                    }
                    System.out.println(i+1 + ".[" + mark + "] " + list[i]);
                }
                printLine();
            } else if (line.startsWith("mark ")) {
                printLine();
                String number = line.substring(5);
                int val = Integer.parseInt(number);
                if (isChecked[val-1]) {
                    System.out.println("Oops, this item has already been marked!");
                    printLine();
                } else {
                    isChecked[val-1] = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + list[val-1]);
                    printLine();
                }
            } else if (line.startsWith("unmark ")) {
                printLine();
                String number = line.substring(7);
                int val = Integer.parseInt(number);
                if (!isChecked[val-1]) {
                    System.out.println("Oops, this item is already unmarked!");
                    printLine();
                } else {
                    isChecked[val-1] = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + list[val-1]);
                    printLine();
                }
            } else {
                printLine();
                list[size] = line;
                size++;
                System.out.println("added: " + line);
                printLine();
            }
        }

        sayBye();
    }
}
