package dino;

import java.util.Scanner;

/**
 * Handles the user interactions.
 */
public class Ui {
    Scanner in;

    /**
     * Constructs the Ui by setting up the Scanner.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Reads a line of text input by the user.
     *
     * @return the next line of user input from the console
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints a horizontal divider line in the console.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Draws an ASCII representation of Dino saying hello.
     *
     * @param message the hello message that Dino says
     */
    public void drawDino(String message) {
        System.out.println("               __");
        System.out.println("              / ^_)    " + message);
        System.out.println("     _.----._/ /");
        System.out.println("    /         /");
        System.out.println(" __/ (  | (  |");
        System.out.println("/__.-'|_|--|_|");
    }

    /**
     * Greets the user by displaying a welcome message.
     */
    public void sayHello() {
        this.printLine();
        drawDino("Hello! I'm Dino :)");
        System.out.println("What can I do for you?");
        this.printLine();
    }

    /**
     * Says goodbye to the user by displaying a goodbye message.
     */
    public void sayBye() {
        this.printLine();
        drawDino("Byeee");
        this.printLine();
    }
}
