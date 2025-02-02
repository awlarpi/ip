package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class DeadlineCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test_tasks.txt");
        DeadlineCommand cmd = new DeadlineCommand("Test Deadline", "10/10/2023 1800");
        cmd.execute(tasks, ui, storage);
        assertEquals(1, tasks.getSize());
        assertEquals("[D][ ] Test Deadline (by: 10 Oct 2023, 6:00pm)", tasks.getTask(0).toString());
    }
}
