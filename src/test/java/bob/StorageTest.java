package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class StorageTest {

    @Test
    public void testLoadAndSave() throws IOException, ClassNotFoundException {
        Storage storage = new Storage("data/test_tasks.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("Test ToDo"));
        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("[T][ ] Test ToDo", loadedTasks.get(0).toString());
    }
}