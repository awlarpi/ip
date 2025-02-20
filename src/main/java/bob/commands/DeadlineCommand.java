package bob.commands;

import bob.exceptions.InvalidDateException;
import bob.exceptions.MissingArgumentException;
import bob.models.Deadline;
import bob.models.Task;
import bob.models.TaskList;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand implements Command {
    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand with the specified description and deadline
     * date.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Gets the description of the deadline task.
     *
     * @return The description of the deadline task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the deadline date.
     *
     * @return The deadline date.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidDateException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new MissingArgumentException(
                    "Hey! The description and deadline date cannot be empty. Give me something to work with!");
        }
        Task deadlineTask = new Deadline(description, by);
        tasks.addTask(deadlineTask);
        return "Got it. I've added this task:\n  " + deadlineTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.";
    }
}
