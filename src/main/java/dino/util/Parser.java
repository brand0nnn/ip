package dino.util;

import dino.tasks.Type;
import dino.commands.AddCommand;
import dino.commands.Command;
import dino.commands.DeleteCommand;
import dino.commands.ExitCommand;
import dino.commands.ListCommand;
import dino.commands.FindCommand;
import dino.commands.MarkOrUnmarkCommand;
import dino.exception.DinoException;
import dino.exception.ExceptionMessage;

/**
 * Parses the user input command and returns the corresponding command object.
 */
public class Parser {
    /**
     * Parses the given input string and produces a corresponding command object.
     *
     * @param input The user input string representing the command
     * @return The command object corresponding to the user input
     * @throws DinoException if the input is invalid or missing required argument
     */
    public Command parseInput(String input) throws DinoException {
        if (input.contains("|")) {
            throw new DinoException(ExceptionMessage.ILLEGAL_CHARACTER);
        }
        String[] split = input.split(" ", 2);
        int splitSize = split.length;
        String command = split[0].toLowerCase().trim();
        boolean isSingleWord = splitSize == 1;
        boolean isListOrExitCommand = command.equals("list") || command.equals("bye");
        if (isSingleWord && !isListOrExitCommand) {
            throw new DinoException(ExceptionMessage.INVALID_COMMAND);
        }

        switch(command) {
        case "list":
            return new ListCommand(input);
        case "bye":
            return new ExitCommand(input);
        case "mark":
            return new MarkOrUnmarkCommand(input, true);
        case "unmark":
            return new MarkOrUnmarkCommand(input, false);
        case "todo":
            return new AddCommand(input, Type.TODO);
        case "event":
            return new AddCommand(input, Type.EVENT);
        case "deadline":
            return new AddCommand(input, Type.DEADLINE);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new DinoException(ExceptionMessage.INVALID_COMMAND);
        }
    }
}
