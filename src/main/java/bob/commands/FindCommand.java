package bob.commands;

import java.util.List;

import bob.models.Task;
import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that match the keyword.
     *
     * @param tasks The list of tasks to search within.
     * @param ui The UI to display the results.
     * @param storage The storage to save any changes (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.reply("No matching tasks found.");
        } else {
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            ui.reply(response.toString().trim());
        }
    }
}
