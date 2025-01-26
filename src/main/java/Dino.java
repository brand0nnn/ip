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
        boolean goodbye = false;
        String line;
        Scanner in = new Scanner(System.in);
        while (!goodbye) {
            line = in.nextLine();
            if (line.equals("bye")) {
                goodbye = true;
            }
            else {
                printLine();
                System.out.println(line);
                printLine();
            }
        }
        sayBye();
    }
}
