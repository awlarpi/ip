package bob.commands;

import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class UnmarkCommand implements Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new IndexOutOfBoundsException("Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        tasks.getTask(taskNumber - 1).markAsNotDone();
        ui.reply("OK, I've marked this task as not done yet:\n" + tasks.getTask(taskNumber - 1));
    }
}