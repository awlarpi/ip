package bob.commands;

import bob.models.Task;
import bob.models.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber The task number to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks) {
        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new IndexOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        Task removedTask = tasks.deleteTask(taskNumber - 1);
        return "Noted. I've removed this task:\n  " + removedTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.";
    }
}
