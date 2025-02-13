package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.models.Task;
import bob.models.TaskList;
import bob.models.ToDo;

public class UnmarkCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test ToDo");
        task.markAsDone();
        tasks.addTask(task);
        UnmarkCommand cmd = new UnmarkCommand(1);
        String result = cmd.execute(tasks);
        assertEquals("OK, I've marked this task as not done yet:\n[T][ ] Test ToDo", result);
        assertEquals("[T][ ] Test ToDo", tasks.getTask(0).toString());
    }
}
