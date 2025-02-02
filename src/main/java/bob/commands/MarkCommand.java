package bob.commands;

import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class MarkCommand implements Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new IndexOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        tasks.getTask(taskNumber - 1).markAsDone();
        ui.reply("Nice! I've marked this task as done:\n" + tasks.getTask(taskNumber - 1));
    }
}
