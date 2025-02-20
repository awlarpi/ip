package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.exceptions.TaskNumberOutOfBoundsException;
import bob.models.TaskList;
import bob.models.ToDo;

public class MarkCommandTest {

    @Test
    public void testExecute() throws TaskNumberOutOfBoundsException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Test ToDo"));
        MarkCommand cmd = new MarkCommand(1);
        String result = cmd.execute(tasks);
        assertEquals("Nice! I've marked this task as done:\n[T][X] Test ToDo", result);
        assertEquals("[T][X] Test ToDo", tasks.getTask(0).toString());
    }

    @Test
    public void testExecute_invalidIndex() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Test ToDo"));
        MarkCommand cmd = new MarkCommand(2);
        try {
            cmd.execute(tasks);
        } catch (TaskNumberOutOfBoundsException e) {
            assertEquals("Whoa there! That task number is out of bounds. Try again, buddy!", e.getMessage());
        }
    }
}
