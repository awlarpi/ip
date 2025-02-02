package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import bob.models.TaskList;
import bob.models.ToDo;
import bob.storage.Storage;
import bob.ui.Ui;

public class ListCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Test ToDo"));
        Ui ui = new Ui();
        Storage storage = new Storage("data/test_tasks.txt");
        ListCommand cmd = new ListCommand();
        cmd.execute(tasks, ui, storage);
        assertEquals("Here are the tasks in your list:\n1. [T][ ] Test ToDo", ui.getLastReply());
    }
}
