package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import bob.models.TaskList;
import bob.models.ToDo;
import bob.storage.Storage;
import bob.ui.Ui;

public class FindCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("read book"));
        tasks.addTask(new ToDo("return book"));
        Ui ui = new Ui();
        Storage storage = new Storage("data/test_tasks.txt");
        FindCommand cmd = new FindCommand("book");
        cmd.execute(tasks, ui, storage);
        assertEquals(
                "Here are the matching tasks in your list:\n1. [T][ ] read book\n2. [T][ ] return book",
                ui.getLastReply());
    }
}
