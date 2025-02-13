package bob.commands;

import bob.models.TaskList;
import javafx.application.Platform;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {
    @Override
    public String execute(TaskList tasks) {
        Platform.exit();
        return "Bye! Hope to see you again soon!";
    }
}
