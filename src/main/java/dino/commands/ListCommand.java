package dino.commands;

import java.util.ArrayList;
import dino.Task;
import dino.TaskList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    public void execute(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            System.out.println(i+1 + ".[" + tasks.get(i).getTypeIcon() + "][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription() + tasks.get(i).getDate());
        }
    }
}
