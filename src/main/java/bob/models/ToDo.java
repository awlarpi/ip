package bob.models;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo with the specified description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}