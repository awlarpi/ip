package bob.exceptions;

/**
 * Represents an exception thrown when the user inputs an unknown command.
 */
public class UnknownCommandException extends CommandException {
    public UnknownCommandException() {
        super("Yikes! I'm not sure what command you're trying to use. Try again!");
    }
}
