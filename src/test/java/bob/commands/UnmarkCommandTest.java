package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import bob.models.Task;
import bob.models.TaskList;
import bob.models.ToDo;
import bob.storage.Storage;
import bob.ui.Ui;

public class UnmarkCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test ToDo");
        task.markAsDone();
        tasks.addTask(task);
        Ui ui = new Ui();
        Storage storage = new Storage("data/test_tasks.txt");
        UnmarkCommand cmd = new UnmarkCommand(1);
        cmd.execute(tasks, ui, storage);
        assertEquals("[T][ ] Test ToDo", tasks.getTask(0).toString());
    }
}
