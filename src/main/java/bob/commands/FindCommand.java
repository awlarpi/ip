package bob.commands;

import java.util.List;
import bob.models.Task;
import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.reply("No matching tasks found.");
        } else {
            StringBuilder response =
                    new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            ui.reply(response.toString().trim());
        }
    }
}
