package bob.commands;

import java.io.IOException;

import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents a command that can be executed.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws IOException If an I/O error occurs.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
