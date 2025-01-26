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
                for (int i = 0; i < size; i++) {
                    System.out.println(i+1 + ". " + list[i]);
                }
                printLine();
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
