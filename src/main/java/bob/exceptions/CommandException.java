package bob.exceptions;

/**
 * Represents an exception thrown when an error occurs while executing a command.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}
