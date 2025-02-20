package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.exceptions.TaskNumberOutOfBoundsException;
import bob.models.Task;
import bob.models.TaskList;
import bob.models.ToDo;

public class UnmarkCommandTest {

    @Test
    public void testExecute() throws TaskNumberOutOfBoundsException {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test ToDo");
        task.markAsDone();
        tasks.addTask(task);
        UnmarkCommand cmd = new UnmarkCommand(1);
        String result = cmd.execute(tasks);
        assertEquals("OK, I've marked this task as not done yet:\n[T][ ] Test ToDo", result);
        assertEquals("[T][ ] Test ToDo", tasks.getTask(0).toString());
    }

    @Test
    public void testExecute_invalidIndex() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Test ToDo"));
        UnmarkCommand cmd = new UnmarkCommand(2);
        try {
            cmd.execute(tasks);
        } catch (TaskNumberOutOfBoundsException e) {
            assertEquals("Whoa there! That task number is out of bounds. Try again, buddy!", e.getMessage());
        }
    }
}
