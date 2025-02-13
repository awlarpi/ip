package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.models.Task;
import bob.models.TaskList;
import bob.models.ToDo;

public class DeleteCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test ToDo");
        tasks.addTask(task);
        DeleteCommand cmd = new DeleteCommand(1);
        String result = cmd.execute(tasks);
        assertEquals("Noted. I've removed this task:\n  [T][ ] Test ToDo\nNow you have 0 tasks in the list.", result);
        assertEquals(0, tasks.getSize());
    }
}
