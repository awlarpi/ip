package bob.commands;

import bob.exceptions.TaskNumberOutOfBoundsException;
import bob.models.TaskList;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand implements Command {
    private int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber The task number to unmark as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks) throws TaskNumberOutOfBoundsException {
        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new TaskNumberOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        tasks.getTask(taskNumber - 1).markAsNotDone();
        return "OK, I've marked this task as not done yet:\n" + tasks.getTask(taskNumber - 1);
    }
}
