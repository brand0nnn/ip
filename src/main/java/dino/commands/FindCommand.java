package dino.commands;

import java.util.ArrayList;
import dino.tasks.Task;
import dino.util.TaskList;
import dino.util.Ui;

public class FindCommand extends Command {
    private Ui ui;

    public FindCommand(String command) {
        super(command);
        ui = new Ui();
    }

    public void execute(TaskList tasklist) {
        String keyword = command.split(" ", 2)[1].trim();
        ArrayList<Task> tasks = tasklist.getTasks();
        int size = tasks.size();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < size; i++) {
            String description = tasks.get(i).getDescription();
            if (description.contains(keyword)) {
                System.out.println(i+1 + ".[" + tasks.get(i).getTypeIcon() + "][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription() + tasks.get(i).getDate());
            }
        }
    }
}
