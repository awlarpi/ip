package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDos("Test ToDo");
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());
        assertEquals("[T][ ] Test ToDo", taskList.getTask(0).toString());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDos("Test ToDo");
        taskList.addTask(task);
        Task removedTask = taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
        assertEquals("[T][ ] Test ToDo", removedTask.toString());
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDos("Test ToDo");
        taskList.addTask(task);
        assertEquals("[T][ ] Test ToDo", taskList.getTask(0).toString());
    }
}