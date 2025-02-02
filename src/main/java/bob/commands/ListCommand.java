package bob.commands;

import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.reply("Here are the tasks in your list:\n" + tasks);
    }
}