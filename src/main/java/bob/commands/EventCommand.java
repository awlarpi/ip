package bob.commands;

import bob.exceptions.InvalidDateException;
import bob.exceptions.MissingArgumentException;
import bob.models.Event;
import bob.models.Task;
import bob.models.TaskList;

/**
 * Represents a command to add an event task.
 */
public class EventCommand implements Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an EventCommand with the specified description, start time, and
     * end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the description of the event task.
     *
     * @return The description of the event task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getTo() {
        return to;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidDateException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new MissingArgumentException(
                    "Hey! The description, start time, and end time cannot be empty. Give me something to work with!");
        }
        Task eventTask = new Event(description, from, to);
        tasks.addTask(eventTask);
        return "Got it. I've added this task:\n  " + eventTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.";
    }
}
