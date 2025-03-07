package dino.commands;

import dino.util.TaskList;
import dino.exception.DinoException;

/**
 * Represents a MarkOrUnmarkCommand to mark or unmark a specified task in the task list.
 */
public class MarkOrUnmarkCommand extends Command {
    boolean mark;

    /**
     * Constructs a MarkOrUnmarkCommand with the specified input command.
     *
     * @param command The user input command specified
     * @param mark True for mark, False for unmark
     */
    public MarkOrUnmarkCommand(String command, boolean mark) {
        super(command);
        this.mark = mark;
    }

    /**
     * Executes the MarkOrUnmarkCommand by marking or unmarking a task in the task list based on the specified input.
     *
     * @param tasks The TaskList with the task to be marked or unmarked
     */
    public void execute(TaskList tasks) {
        try {
            tasks.markOrUnmarkTask(command, mark);
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index specified does not exist");
        }
    }
}
