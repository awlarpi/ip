package bob.exceptions;

/**
 * Represents an exception thrown when the date format is invalid.
 */
public class InvalidDateException extends CommandException {
    public InvalidDateException() {
        super("Hey! The date and time format is invalid. Please use the format: dd/MM/yyyy HHmm");
    }
}
