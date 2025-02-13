package bob.commands;

import bob.models.Task;
import bob.models.TaskList;
import bob.models.ToDo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand implements Command {
    private String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the todo task.
     *
     * @return The description of the todo task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String execute(TaskList tasks) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description of a todo cannot be empty. Give me something to do!");
        }
        Task todoTask = new ToDo(description);
        tasks.addTask(todoTask);
        return "Got it. I've added this task:\n  " + todoTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.";
    }
}
