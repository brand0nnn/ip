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
        boolean[] checked = new boolean[100];
        int size = 0;
        boolean goodbye = false;
        String line;
        Scanner in = new Scanner(System.in);

        while (!goodbye) {
            line = in.nextLine();
            if (line.equals("bye")) {
                goodbye = true;
            }
            else if (line.equals("list")) {
                printLine();
                char check;
                for (int i = 0; i < size; i++) {
                    if (checked[i]) {
                        check = 'X';
                    }
                    else {
                        check = ' ';
                    }
                    System.out.println(i+1 + ".[" + check + "] " + list[i]);
                }
                printLine();
            }
            else if (line.startsWith("mark ")) {
                printLine();
                String number = line.substring(5);
                int val = Integer.parseInt(number);
                if (checked[val-1]) {
                    System.out.println("Oops, this item has already been marked!");
                    printLine();
                }
                else {
                    checked[val - 1] = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + list[val - 1]);
                    printLine();
                }
            }
            else if (line.startsWith("unmark ")) {
                printLine();
                String number = line.substring(7);
                int val = Integer.parseInt(number);
                if (!checked[val-1]) {
                    System.out.println("Oops, this item is already unmarked!");
                    printLine();
                }
                else {
                    checked[val-1] = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + list[val-1]);
                    printLine();
                }
            }
            else {
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
