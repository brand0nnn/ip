package dino;

import dino.commands.Command;
import dino.exception.DinoException;

public class Dino {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    private static Parser parser;

    public Dino(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(filePath));
        parser = new Parser();
    }

    public void run(String filePath) {
        ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parseInput(fullCommand);
                ui.printLine();
                c.execute(tasks);
                ui.printLine();
                isExit = c.isExit();
            } catch (DinoException e) {
                System.out.println(e.getMessage());
            }
        }
        storage.save(filePath, tasks);
        ui.sayBye();
    }

    public static void main(String[] args) {
        String filePath = "data/savefile.txt";
        new Dino(filePath).run(filePath);
    }
}
