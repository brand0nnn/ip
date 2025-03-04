package dino.commands;

import dino.TaskList;
import dino.exception.DinoException;

public class MarkOrUnmarkCommand extends Command {
    boolean mark;

    public MarkOrUnmarkCommand(String command, boolean mark) {
        super(command);
        this.mark = mark;
    }

    public void execute(TaskList tasks) {
        try {
            tasks.markOrUnmarkTask(command, mark);
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }
}
