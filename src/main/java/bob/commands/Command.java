package bob.commands;

import bob.models.TaskList;

/**
 * Represents a command that can be executed.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param tasks The task list.
     */
    String execute(TaskList tasks);
}
