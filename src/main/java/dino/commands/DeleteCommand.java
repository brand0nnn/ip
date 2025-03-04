package dino.commands;

import dino.TaskList;
import dino.exception.DinoException;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks) {
        try {
            tasks.deleteTask(command);
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }
}
