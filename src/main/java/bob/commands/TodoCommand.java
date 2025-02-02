package bob.commands;

import bob.models.Task;
import bob.models.TaskList;
import bob.models.ToDo;
import bob.storage.Storage;
import bob.ui.Ui;

public class TodoCommand implements Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description of a todo cannot be empty. Give me something to do!");
        }
        Task todoTask = new ToDo(description);
        tasks.addTask(todoTask);
        ui.reply("Got it. I've added this task:\n  " + todoTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
