package dino.commands;

import java.time.format.DateTimeParseException;

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
        } catch (DateTimeParseException e) {
            System.out.println("Please enter date in the format yyyy-MM-dd for deadlines, and yyyy-MM-dd HH:mm for events");
        }
    }
}
