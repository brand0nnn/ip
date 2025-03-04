package dino.commands;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return true;
    }
}
