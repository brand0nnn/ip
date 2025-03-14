package dino;

import dino.commands.Command;
import dino.exception.DinoException;
import dino.util.Parser;
import dino.util.Storage;
import dino.util.TaskList;
import dino.util.Ui;

public class Dino {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    private static Parser parser;
    private static final String filePath = "data/savefile.txt";

    public Dino(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load(filePath));
            parser = new Parser();
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
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
        new Dino(filePath).run(filePath);
    }
}
