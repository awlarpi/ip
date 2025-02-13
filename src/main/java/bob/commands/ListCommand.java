package bob.commands;

import bob.models.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks;
    }
}
