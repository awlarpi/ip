package bob.commands;

import bob.exceptions.MissingArgumentException;
import bob.exceptions.UnknownCommandException;

/**
 * Parses user input into commands.
 */
public class Parser {
    /**
     * Parses the user input into a command.
     *
     * @param input The user input.
     * @return The command.
     * @throws MissingArgumentException If the command format is incorrect.
     * @throws UnknownCommandException
     */
    public static Command parse(String input) throws MissingArgumentException, UnknownCommandException {
        String[] parts = input.split(" ", 2);
        String firstWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (firstWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMarkCommand(arguments);
        case "unmark":
            return parseUnmarkCommand(arguments);
        case "todo":
        case "t":
            return parseTodoCommand(arguments);
        case "deadline":
        case "d":
            return parseDeadlineCommand(arguments);
        case "event":
        case "e":
            return parseEventCommand(arguments);
        case "find":
            return new FindCommand(arguments.trim());
        case "delete":
            return new DeleteCommand(Integer.parseInt(arguments.trim()));
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the arguments for a mark command.
     *
     * @param arguments The arguments for the mark command.
     * @return The mark command.
     */
    private static Command parseMarkCommand(String arguments) {
        return new MarkCommand(Integer.parseInt(arguments.trim()));
    }

    /**
     * Parses the arguments for an unmark command.
     *
     * @param arguments The arguments for the unmark command.
     * @return The unmark command.
     */
    private static Command parseUnmarkCommand(String arguments) {
        return new UnmarkCommand(Integer.parseInt(arguments.trim()));
    }

    /**
     * Parses the arguments for a todo command.
     *
     * @param arguments The arguments for the todo command.
     * @return The todo command.
     */
    private static Command parseTodoCommand(String arguments) {
        return new TodoCommand(arguments.trim());
    }

    /**
     * Parses the arguments for a deadline command.
     *
     * @param arguments The arguments for the deadline command.
     * @return The deadline command.
     * @throws MissingArgumentException If the deadline command format is incorrect.
     */
    private static Command parseDeadlineCommand(String arguments) throws MissingArgumentException {
        String[] deadlineParts = arguments.split(" /by ");
        if (deadlineParts.length < 2) {
            throw new MissingArgumentException(
                    "Yikes! The deadline command format is all wrong. Give it another shot!");
        }
        return new DeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
    }

    /**
     * Parses the arguments for an event command.
     *
     * @param arguments The arguments for the event command.
     * @return The event command.
     * @throws MissingArgumentException If the event command format is incorrect.
     */
    private static Command parseEventCommand(String arguments) throws MissingArgumentException {
        String[] eventParts = arguments.split(" /from ");
        if (eventParts.length < 2) {
            throw new MissingArgumentException("Yikes! The event command format is all wrong. Give it another shot!");
        }
        String[] times = eventParts[1].split(" /to ");
        if (times.length < 2) {
            throw new MissingArgumentException("Yikes! The event command format is all wrong. Give it another shot!");
        }
        return new EventCommand(eventParts[0].trim(), times[0].trim(), times[1].trim());
    }
}
