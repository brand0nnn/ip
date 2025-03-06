package dino.commands;

import dino.util.TaskList;
import dino.exception.DinoException;

/**
 * Represents a DeleteCommand to delete a command from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a DeleteCommand with the specified input command.
     *
     * @param command The input command specified by the user
     */
    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Executes the command by deleting the specified task from TaskList.
     *
     * @param tasks The TaskList where the specified task should be deleted from
     */
    public void execute(TaskList tasks) {
        try {
            tasks.deleteTask(command);
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }
}
