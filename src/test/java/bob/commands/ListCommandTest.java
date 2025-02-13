package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.models.TaskList;
import bob.models.ToDo;

public class ListCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Test ToDo"));
        ListCommand cmd = new ListCommand();
        String result = cmd.execute(tasks);
        assertEquals("Here are the tasks in your list:\n1. [T][ ] Test ToDo", result);
    }
}
