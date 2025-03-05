package dino.commands;

/**
 * Represents an ExitCommand to terminate the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructs an ExitCommand with the specified input command.
     *
     * @param command The input command specified by the user.
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Returns true to tell the program to terminate.
     *
     * @return true when ExitCommand is used
     */
    public boolean isExit() {
        return true;
    }
}
