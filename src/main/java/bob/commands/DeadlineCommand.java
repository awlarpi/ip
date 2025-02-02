package bob.commands;

import bob.models.Deadline;
import bob.models.Task;
import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class DeadlineCommand implements Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public String getDescription() {
        return description;
    }

    public String getBy() {
        return by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description.isEmpty() || by.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description and deadline date cannot be empty. Give me something to work with!");
        }
        Task deadlineTask = new Deadline(description, by);
        tasks.addTask(deadlineTask);
        ui.reply("Got it. I've added this task:\n  " + deadlineTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
