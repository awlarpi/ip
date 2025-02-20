package bob.exceptions;

/**
 * Represents an exception thrown when the task number is out of bounds.
 */
public class TaskNumberOutOfBoundsException extends CommandException {
    public TaskNumberOutOfBoundsException(String message) {
        super(message);
    }
}
