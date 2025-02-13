package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.models.TaskList;
import bob.models.ToDo;

public class FindCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("read book"));
        tasks.addTask(new ToDo("return book"));
        FindCommand cmd = new FindCommand("book");
        String result = cmd.execute(tasks);
        assertEquals("Here are the matching tasks in your list:\n1. [T][ ] read book\n2. [T][ ] return book", result);
    }
}
