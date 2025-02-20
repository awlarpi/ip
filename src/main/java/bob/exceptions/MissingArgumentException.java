package bob.exceptions;

/**
 * Represents an exception thrown when an argument is missing.
 */
public class MissingArgumentException extends CommandException {
    public MissingArgumentException(String message) {
        super(message);
    }
}
