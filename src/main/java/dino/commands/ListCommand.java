package dino.commands;

import java.util.ArrayList;
import dino.tasks.Task;
import dino.util.TaskList;

/**
 * Represents a ListCommand to list all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the specified input command.
     *
     * @param command The input command specified by the user
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the ListCommand by printing all the tasks from TaskList.
     *
     * @param taskList The TaskList to print all the tasks from
     */
    public void execute(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            System.out.println(i+1 + ".[" + tasks.get(i).getTypeIcon() + "][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription() + tasks.get(i).getDate());
        }
    }
}
