package dino;

import dino.commands.AddCommand;
import dino.commands.Command;
import dino.commands.DeleteCommand;
import dino.commands.ExitCommand;
import dino.commands.ListCommand;
import dino.commands.FindCommand;
import dino.commands.MarkOrUnmarkCommand;
import dino.exception.DinoException;
import dino.exception.ExceptionMessage;

public class Parser {
    public Command parseInput(String input) throws DinoException{
        String[] split = input.split(" ", 2);
        int splitSize = split.length;
        String command = split[0].toLowerCase();

        switch(command) {
        case "list":
            return new ListCommand(input);
        case "bye":
            return new ExitCommand(input);
        case "mark":
            if (splitSize == 1) {
                throw new DinoException(ExceptionMessage.INVALID_COMMAND);
            }
            return new MarkOrUnmarkCommand(input, true);
        case "unmark":
            if (splitSize == 1) {
                throw new DinoException(ExceptionMessage.INVALID_COMMAND);
            }
            return new MarkOrUnmarkCommand(input, false);
        case "todo":
            if (splitSize == 1) {
                throw new DinoException(ExceptionMessage.INVALID_COMMAND);
            }
            return new AddCommand(input, Type.TODO);
        case "event":
            if (splitSize == 1) {
                throw new DinoException(ExceptionMessage.INVALID_COMMAND);
            }
            return new AddCommand(input, Type.EVENT);
        case "deadline":
            if (splitSize == 1) {
                throw new DinoException(ExceptionMessage.INVALID_COMMAND);
            }
            return new AddCommand(input, Type.DEADLINE);
        case "delete":
            if (splitSize == 1) {
                throw new DinoException(ExceptionMessage.INVALID_COMMAND);
            }
            return new DeleteCommand(input);
        case "find":
            if (splitSize == 1) {
                throw new DinoException(ExceptionMessage.INVALID_COMMAND);
            }
            return new FindCommand(input);
        default:
            throw new DinoException(ExceptionMessage.INVALID_COMMAND);
        }
    }
}
