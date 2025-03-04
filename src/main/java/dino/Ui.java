package dino;

import java.util.Scanner;

public class Ui {
    Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void drawDino(String message) {
        System.out.println("               __");
        System.out.println("              / ^_)    " + message);
        System.out.println("     _.----._/ /");
        System.out.println("    /         /");
        System.out.println(" __/ (  | (  |");
        System.out.println("/__.-'|_|--|_|");
    }

    public void sayHello() {
        this.printLine();
        drawDino("Hello! I'm Dino :)");
        System.out.println("What can I do for you?");
        this.printLine();
    }

    public void sayBye() {
        this.printLine();
        drawDino("Byeee");
        this.printLine();
    }
}
