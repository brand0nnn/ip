package dino.commands;

import dino.TaskList;
import dino.Type;
import dino.exception.DinoException;

public class AddCommand extends Command {
    Type type;

    public AddCommand(String command, Type type) {
        super(command);
        this.type = type;
    }

    public void execute(TaskList tasks) {
        try {
            tasks.addTask(command, type);
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }
}
