package bob.commands;

import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No operation needed for ByeCommand
    }
}