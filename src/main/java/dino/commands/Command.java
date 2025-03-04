package dino.commands;

import dino.TaskList;

public class Command {
    String command;

    Command(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks) {

    }

    public boolean isExit() {
        return false;
    }
}
