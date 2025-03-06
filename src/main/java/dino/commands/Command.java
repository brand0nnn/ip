package dino.commands;

import dino.util.TaskList;

/**
 * Represents a general Command that contains the user input command.
 */
public class Command {
    String command;

    /**
     * Constructs a Command with the user input command.
     *
     * @param command The input command specified by the user
     */
    Command(String command) {
        this.command = command;
    }

    /**
     * Executes the command based on what command it is, empty if none.
     *
     * @param tasks The TaskList to execute the command on
     */
    public void execute(TaskList tasks) {

    }

    /**
     * Returns false to keep the program running if ExitCommand is not received.
     *
     * @return false if not command is not ExitCommand
     */
    public boolean isExit() {
        return false;
    }
}
