package bob.commands;

import bob.models.Task;
import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class DeleteCommand implements Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new IndexOutOfBoundsException("Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        Task removedTask = tasks.deleteTask(taskNumber - 1);
        ui.reply("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.getSize()
                + " tasks in the list.");
    }
}