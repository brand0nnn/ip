package dino.commands;

import java.time.format.DateTimeParseException;

import dino.TaskList;
import dino.Type;
import dino.exception.DinoException;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {
    Type type;

    /**
     * Constructs an AddCommand with the specified input command and task type.
     *
     * @param command The input command specified by the user
     * @param type The type of task that is being added (Todo, Deadline or Event)
     */
    public AddCommand(String command, Type type) {
        super(command);
        this.type = type;
    }

    /**
     * Executes the command by adding the task into the TaskList,
     * an error message is printed if an error occurs.
     *
     * @param tasks The TaskList where the new task is to be added to
     */
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
